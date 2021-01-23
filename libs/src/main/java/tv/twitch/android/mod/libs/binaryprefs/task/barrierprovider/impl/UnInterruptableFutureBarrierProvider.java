package tv.twitch.android.mod.libs.binaryprefs.task.barrierprovider.impl;

import tv.twitch.android.mod.libs.binaryprefs.event.ExceptionHandler;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.FutureBarrier;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.impl.UninterruptableFutureBarrier;
import tv.twitch.android.mod.libs.binaryprefs.task.barrierprovider.FutureBarrierProvider;

import java.util.concurrent.Future;

public final class UnInterruptableFutureBarrierProvider implements FutureBarrierProvider {

    @Override
    public <T> FutureBarrier<T> get(Future<T> submit, ExceptionHandler handler) {
        return new UninterruptableFutureBarrier<>(submit, handler);
    }
}
