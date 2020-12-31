package tv.twitch.android.mod.bridge;


public interface ISleepTimer {
    void start(int seconds);

    void cancel();
}
