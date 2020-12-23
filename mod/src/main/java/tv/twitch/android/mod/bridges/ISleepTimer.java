package tv.twitch.android.mod.bridges;


public interface ISleepTimer {
    void start(int seconds);

    void cancel();
}
