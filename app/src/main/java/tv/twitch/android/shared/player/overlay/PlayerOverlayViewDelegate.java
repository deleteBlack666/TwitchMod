package tv.twitch.android.shared.player.overlay;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import io.reactivex.subjects.PublishSubject;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.bridges.Hooks;
import tv.twitch.android.mod.bridges.ResourcesManager;


public class PlayerOverlayViewDelegate extends BaseViewDelegate {
    private final ImageView timerSleepButton; // TODO: __INJECT_FIELD

    /* ... */

    public PlayerOverlayViewDelegate(Context context, View view, Object iChromecastHelper, Object experience, OverlayLayoutController overlayLayoutController2) {
        /* ... */

        timerSleepButton = getTimerSleepButton(view); // TODO: __INJECT_CODE
        Hooks.setupTimerSleepButton(context, timerSleepButton); // TODO: __INJECT_CODE
    }

    public final OverlayLayoutController getOverlayLayoutController() {
        return null;
    }

    public final PublishSubject<PlayerOverlayEvents> getPlayerOverlayEventsSubject() {
        return null;
    }

    public final void setLockButtonVisible(boolean z) { // TODO: __INJECT_METHOD
        getBottomPlayerControlOverlayViewDelegate().setLockButtonVisible(z);
    }

    private ImageView getTimerSleepButton(View view) { // TODO: __INJECT_METHOD
        return view.findViewById(ResourcesManager.getId("sleep_timer_button"));
    }

    private final BottomPlayerControlOverlayViewDelegate getBottomPlayerControlOverlayViewDelegate() {
        return null;
    }

    public final void showUptime(int seconds) { // TODO: __INJECT_METHOD
        if (seconds >= 0) {
            getBottomPlayerControlOverlayViewDelegate().showUptime(seconds);
        } else {
            getBottomPlayerControlOverlayViewDelegate().hideUptime();
        }
    }
}
