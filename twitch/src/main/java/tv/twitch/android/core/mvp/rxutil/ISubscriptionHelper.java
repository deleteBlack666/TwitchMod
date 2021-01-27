package tv.twitch.android.core.mvp.rxutil;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public interface ISubscriptionHelper {
    void asyncSubscribe(Completable completable, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, DisposeOn disposeOn);

    void asyncSubscribe(Completable completable, DisposeOn disposeOn, Function0<Unit> function0);

    <T> void asyncSubscribe(Flowable<T> flowable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void asyncSubscribe(Flowable<T> flowable, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void asyncSubscribe(Maybe<T> maybe, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void asyncSubscribe(Maybe<T> maybe, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void asyncSubscribe(Observable<T> observable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void asyncSubscribe(Observable<T> observable, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void asyncSubscribe(Single<T> single, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void asyncSubscribe(Single<T> single, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    void autoDispose(Disposable disposable, DisposeOn disposeOn);

    void directSubscribe(Completable completable, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, DisposeOn disposeOn);

    void directSubscribe(Completable completable, DisposeOn disposeOn, Function0<Unit> function0);

    <T> void directSubscribe(Flowable<T> flowable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void directSubscribe(Flowable<T> flowable, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void directSubscribe(Maybe<T> maybe, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void directSubscribe(Maybe<T> maybe, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void directSubscribe(Observable<T> observable, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void directSubscribe(Observable<T> observable, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    <T> void directSubscribe(Single<T> single, Function1<? super T, Unit> function1, Function1<? super Throwable, Unit> function12, DisposeOn disposeOn);

    <T> void directSubscribe(Single<T> single, DisposeOn disposeOn, Function1<? super T, Unit> function1);

    void removeDisposable(Disposable disposable);

    final class DefaultImpls {
        public static void autoDispose$default(ISubscriptionHelper iSubscriptionHelper, Disposable disposable, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Observable observable, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Flowable flowable, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Single single, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Maybe maybe, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Completable completable, Function0 function0, Function1 function1, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Observable observable, DisposeOn disposeOn, Function1 function1, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Flowable flowable, DisposeOn disposeOn, Function1 function1, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Single single, DisposeOn disposeOn, Function1 function1, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Maybe maybe, DisposeOn disposeOn, Function1 function1, int i, Object obj) {/* ... */}

        public static void asyncSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Completable completable, DisposeOn disposeOn, Function0 function0, int i, Object obj) {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Observable observable, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Flowable flowable, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Single single, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj) {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Maybe maybe, Function1 function1, Function1 function12, DisposeOn disposeOn, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Completable completable, Function0 function0, Function1 function1, DisposeOn disposeOn, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Observable observable, DisposeOn disposeOn, Function1 function1, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Flowable flowable, DisposeOn disposeOn, Function1 function1, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Single single, DisposeOn disposeOn, Function1 function1, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Maybe maybe, DisposeOn disposeOn, Function1 function1, int i, Object obj)  {/* ... */}

        public static void directSubscribe$default(ISubscriptionHelper iSubscriptionHelper, Completable completable, DisposeOn disposeOn, Function0 function0, int i, Object obj)  {/* ... */}
    }
}