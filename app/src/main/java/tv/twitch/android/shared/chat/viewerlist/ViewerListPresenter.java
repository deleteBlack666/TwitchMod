package tv.twitch.android.shared.chat.viewerlist;



import android.view.View;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tv.twitch.android.core.mvp.presenter.PresenterState;
import tv.twitch.android.core.mvp.presenter.RxPresenter;
import tv.twitch.android.core.mvp.rxutil.DisposeOn;


public class ViewerListPresenter extends RxPresenter<ViewerListPresenter.State, ViewerListViewDelegate> {
    private ViewerListAdapterBinder adapterBinder;
    View v;

    public static abstract class State implements PresenterState {}

    public final void attachViewDelegate(ViewerListViewDelegate viewerListViewDelegate, Object bottomSheetBehaviorViewDelegate2) {
        /* ... */

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });

        setupFilter(viewerListViewDelegate); // TODO: __INJECT_CODE
    }

    private void setupFilter(ViewerListViewDelegate viewerListViewDelegate) { // TODO: __INJECT_METHOD
        this.directSubscribe(viewerListViewDelegate.eventObserver(), DisposeOn.DESTROY, new Function1<ViewerListViewDelegate.Event, Unit>() {
            @Override
            public Unit invoke(ViewerListViewDelegate.Event event) {
                if (event instanceof ViewerListViewDelegate.Event.Filter) {
                    adapterBinder.setFilterText(((ViewerListViewDelegate.Event.Filter) event).getText());
                }
                return Unit.INSTANCE;
            }
        });
    }
}
