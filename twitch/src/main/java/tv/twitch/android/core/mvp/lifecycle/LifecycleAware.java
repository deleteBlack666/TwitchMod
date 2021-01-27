package tv.twitch.android.core.mvp.lifecycle;


public interface LifecycleAware {
    boolean isActive();

    void onActive();

    void onConfigurationChanged();

    void onDestroy();

    void onInactive();

    void onViewDetached();
}
