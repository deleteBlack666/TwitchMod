package tv.twitch.android.core.mvp.presenter;


import io.reactivex.Flowable;


public interface IStateObserver<S> {
    void pushState(S s);

    Flowable<S> stateObserver();
}