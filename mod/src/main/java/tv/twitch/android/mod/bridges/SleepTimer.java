package tv.twitch.android.mod.bridges;

import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

public class SleepTimer implements ISleepTimer {
    private final Object lock = new Object();
    private CountDownTimer mTimer;
    private final Callback mCallback;

    public interface Callback {
        void onDone();

        void onInfoTick(long milliseconds);
    }

    public SleepTimer(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void start(int seconds) {
        synchronized (lock) {
            if (mTimer != null) {
                mTimer.cancel();
            }
            mTimer = new CountDownTimer(seconds * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    int secondsUntilFinished = (int) TimeUnit.MILLISECONDS.toSeconds(l);
                    if (secondsUntilFinished == 60 || secondsUntilFinished == 10) {
                        mCallback.onInfoTick(l);
                    }
                }

                @Override
                public void onFinish() {
                    mCallback.onDone();
                }
            };
            mTimer.start();
        }
    }

    @Override
    public void cancel() {
        if (mTimer != null) {
            synchronized (lock) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        }
    }
}
