package tv.twitch.android.mod.libs.binaryprefs.task.barrierprovider;

import tv.twitch.android.mod.libs.binaryprefs.event.ExceptionHandler;
import tv.twitch.android.mod.libs.binaryprefs.task.barrier.FutureBarrier;

import java.util.concurrent.Future;

public interface FutureBarrierProvider {
    <T> FutureBarrier<T> get(Future<T> submit, ExceptionHandler handler);
}
