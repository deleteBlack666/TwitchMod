package tv.twitch.android.core.mvp.viewdelegate;


import io.reactivex.Flowable;


public interface IEventDispatcher<E> {
    Flowable<E> eventObserver();

    void pushEvent(E e);
}