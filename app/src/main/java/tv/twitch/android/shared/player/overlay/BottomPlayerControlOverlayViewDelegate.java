package tv.twitch.android.shared.player.overlay;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import tv.twitch.android.mod.bridge.interfaces.IBottomPlayerControlOverlayViewDelegate;
import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.mod.hooks.HookJump;
import tv.twitch.android.mod.setting.PreferenceManager;
import tv.twitch.android.mod.view.StreamUptimeView;


public class BottomPlayerControlOverlayViewDelegate implements IBottomPlayerControlOverlayViewDelegate { // TODO: __IMPLEMENT
    private BottomPlayerControlListener mBottomPlayerControlListener = new EmptyBottomPlayerControlListener();

    /* ... */

    private ImageView refreshButton; // TODO: __INJECT_FIELD
    private ImageView lockButton; // TODO: __INJECT_FIELD
    private ImageView uptimeIcon; // TODO: __INJECT_FIELD
    private StreamUptimeView uptimeView; // TODO: __INJECT_FIELD

    /* ... */

    public interface BottomPlayerControlListener {
        /* ... */

        void onRefreshClicked(); // TODO: __INJECT_METHOD
    }

    public BottomPlayerControlOverlayViewDelegate(Context context, View view) {
        /* ... */

        setupRefreshButton(view); // TODO: __INJECT_CODE
        setupLockButton(view); // TODO: __INJECT_CODE
        setupUptime(view); // TODO: __INJECT_CODE
    }

    public void updateLockButtonState() { // TODO: __INJECT_METHOD
        HookController.updatePlayerLockButtonState(this.lockButton);
    }

    private void setupLockButton(View view) { // TODO: __INJECT_METHOD
        this.lockButton = (ImageView) HookController.setupPlayerLockButton(view, this);
        updateLockButtonState();
    }

    public void setLockButtonVisible(boolean z) { // TODO: __INJECT_METHOD
        HookController.changeLockButtonVisibility(this.lockButton, z);
    }

    public void clickRefresh() { // TODO: __INJECT_METHOD
        if (mBottomPlayerControlListener != null) {
            mBottomPlayerControlListener.onRefreshClicked();
        }
    }


    private void setupRefreshButton(View view) { // TODO: __INJECT_METHOD
        this.refreshButton = (ImageView) HookController.setupPlayerRefreshButton(view, this);
        if (!PreferenceManager.INSTANCE.shouldShowPlayerRefreshButton() && this.refreshButton != null) {
            this.refreshButton.setVisibility(View.GONE);
        }
    }

    private void setupUptime(View view) { // TODO: __INJECT_METHOD
        this.uptimeView = (StreamUptimeView) HookController.setupPlayerUptime(view, this);
        this.uptimeIcon = (ImageView) HookController.setupPlayerUptimeIcon(view, this);

        hideUptime();
    }

    public void showUptime(int seconds) { // TODO: __INJECT_METHOD
        if (!HookJump.shouldShowStreamUptime())
            return;

        if (this.uptimeIcon != null) {
            this.uptimeIcon.setVisibility(View.VISIBLE);
        }

        if (this.uptimeView != null) {
            this.uptimeView.show(seconds);
        }
    }

    public void hideUptime() { // TODO: __INJECT_METHOD
        if (this.uptimeIcon != null) {
            this.uptimeIcon.setVisibility(View.GONE);
        }

        if (this.uptimeView != null) {
            this.uptimeView.hide();
        }
    }
}
