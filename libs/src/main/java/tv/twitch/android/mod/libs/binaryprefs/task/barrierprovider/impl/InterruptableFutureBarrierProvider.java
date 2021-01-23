package tv.twitch.android.mod.libs.binaryprefs.task.barrierprovider.impl;

import tv.twitch.android.mod.libs.binaryprefs.event.ExceptionHandler;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.FutureBarrier;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.impl.InterruptableFutureBarrier;
import tv.twitch.android.mod.libs.binaryprefs.task.barrierprovider.FutureBarrierProvider;

import java.util.concurrent.Future;

public final class InterruptableFutureBarrierProvider implements FutureBarrierProvider {

    @Override
    public <T> FutureBarrier<T> get(Future<T> submit, ExceptionHandler handler) {
        return new InterruptableFutureBarrier<>(submit, handler);
    }
}
