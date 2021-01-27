package tv.twitch.android.core.mvp.presenter;


import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import tv.twitch.android.core.mvp.lifecycle.LifecycleAware;
import tv.twitch.android.core.mvp.rxutil.DisposeOn;
import tv.twitch.android.core.mvp.rxutil.ISubscriptionHelper;


public abstract class BasePresenter implements LifecycleAware, ISubscriptionHelper {
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onActive() {

    }

    @Override
    public void onConfigurationChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onInactive() {

    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void asyncSubscribe(Completable completable, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, DisposeOn disposeOn) {

    }

    @Override
    public void asyncSubscribe(Completable completable, DisposeOn disposeOn, Function0<Unit> function0) {

    }

    @Override
    public <T> void asyncSubscribe(Flowable<T> flowable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void asyncSubscribe(Flowable<T> flowable, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void asyncSubscribe(Maybe<T> maybe, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void asyncSubscribe(Maybe<T> maybe, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void asyncSubscribe(Observable<T> observable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void asyncSubscribe(Observable<T> observable, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void asyncSubscribe(Single<T> single, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void asyncSubscribe(Single<T> single, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public void autoDispose(Disposable disposable, DisposeOn disposeOn) {

    }

    @Override
    public void directSubscribe(Completable completable, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, DisposeOn disposeOn) {

    }

    @Override
    public void directSubscribe(Completable completable, DisposeOn disposeOn, Function0<Unit> function0) {

    }

    @Override
    public <T> void directSubscribe(Flowable<T> flowable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void directSubscribe(Flowable<T> flowable, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void directSubscribe(Maybe<T> maybe, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void directSubscribe(Maybe<T> maybe, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void directSubscribe(Observable<T> observable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void directSubscribe(Observable<T> observable, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public <T> void directSubscribe(Single<T> single, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn) {

    }

    @Override
    public <T> void directSubscribe(Single<T> single, DisposeOn disposeOn, Function1<? super T, Unit> function1) {

    }

    @Override
    public void removeDisposable(Disposable disposable) {

    }
}
