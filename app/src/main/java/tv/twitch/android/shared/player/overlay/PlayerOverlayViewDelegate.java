package tv.twitch.android.shared.player.overlay;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import io.reactivex.subjects.PublishSubject;
import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.hooks.HookController;


public class PlayerOverlayViewDelegate extends BaseViewDelegate {
    private final ImageView timerSleepButton; // TODO: __INJECT_FIELD

    /* ... */

    public PlayerOverlayViewDelegate(Context context, View view, Object iChromecastHelper, Object experience, OverlayLayoutController overlayLayoutController2) {
        /* ... */

        timerSleepButton = (ImageView) HookController.getSleepTimerButton(view); // TODO: __INJECT_CODE
        HookController.setupPlayerSleepTimerButton(context, timerSleepButton); // TODO: __INJECT_CODE
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

    private BottomPlayerControlOverlayViewDelegate getBottomPlayerControlOverlayViewDelegate() {
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
