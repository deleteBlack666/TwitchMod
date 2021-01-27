package tv.twitch.android.core.mvp.presenter;


import io.reactivex.Flowable;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;

public abstract class RxPresenter<S extends PresenterState, VD extends BaseViewDelegate> extends BasePresenter implements IStateObserver<S> {
    @Override
    public void pushState(S s) {

    }

    @Override
    public Flowable<S> stateObserver() {
        return null;
    }
}
