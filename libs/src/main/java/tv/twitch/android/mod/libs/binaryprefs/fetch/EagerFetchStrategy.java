package tv.twitch.android.mod.libs.binaryprefs.fetch;

import tv.twitch.android.mod.libs.binaryprefs.cache.candidates.CacheCandidateProvider;
import tv.twitch.android.mod.libs.binaryprefs.cache.provider.CacheProvider;
import tv.twitch.android.mod.libs.binaryprefs.file.transaction.FileTransaction;
import tv.twitch.android.mod.libs.binaryprefs.file.transaction.TransactionElement;
import tv.twitch.android.mod.libs.binaryprefs.lock.LockFactory;
import tv.twitch.android.mod.libs.binaryprefs.serialization.SerializerFactory;
import tv.twitch.android.mod.libs.binaryprefs.task.TaskExecutor;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.FutureBarrier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public final class EagerFetchStrategy implements FetchStrategy {

    private final Lock readLock;
    private final TaskExecutor taskExecutor;
    private final CacheCandidateProvider candidateProvider;
    private final CacheProvider cacheProvider;
    private final FileTransaction fileTransaction;
    private final SerializerFactory serializerFactory;

    public EagerFetchStrategy(LockFactory lockFactory,
                              TaskExecutor taskExecutor,
                              CacheCandidateProvider candidateProvider,
                              CacheProvider cacheProvider,
                              FileTransaction fileTransaction,
                              SerializerFactory serializerFactory) {
        this.readLock = lockFactory.getReadLock();
        this.taskExecutor = taskExecutor;
        this.candidateProvider = candidateProvider;
        this.cacheProvider = cacheProvider;
        this.fileTransaction = fileTransaction;
        this.serializerFactory = serializerFactory;
        fetchCache();
    }

    private void fetchCache() {
        readLock.lock();
        try {
            FutureBarrier barrier = taskExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    fetchCacheInternal();
                }
            });
            barrier.completeBlockingUnsafe();
        } finally {
            readLock.unlock();
        }
    }

    private void fetchCacheInternal() {
        fileTransaction.lock();
        try {
            if (!shouldFetch()) {
                return;
            }
            for (TransactionElement element : fileTransaction.fetchAll()) {
                String name = element.getName();
                byte[] bytes = element.getContent();
                Object o = serializerFactory.deserialize(name, bytes);
                cacheProvider.put(name, o);
                candidateProvider.put(name);
            }
        } finally {
            fileTransaction.unlock();
        }
    }

    private boolean shouldFetch() {
        Set<String> names = fileTransaction.fetchNames();
        Set<String> cacheKeys = cacheProvider.keys();
        return !cacheKeys.containsAll(names);
    }

    @Override
    public Object getValue(String key, Object defValue) {
        readLock.lock();
        try {
            Object o = cacheProvider.get(key);
            if (o == null) {
                return defValue;
            }
            return serializerFactory.redefineMutable(o);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Map<String, Object> getAll() {
        readLock.lock();
        try {
            Map<String, Object> all = cacheProvider.getAll();
            Map<String, Object> clone = new HashMap<>(all.size());
            for (String key : all.keySet()) {
                Object value = all.get(key);
                Object redefinedValue = serializerFactory.redefineMutable(value);
                clone.put(key, redefinedValue);
            }
            return Collections.unmodifiableMap(clone);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(String key) {
        readLock.lock();
        try {
            return cacheProvider.contains(key);
        } finally {
            readLock.unlock();
        }
    }
}