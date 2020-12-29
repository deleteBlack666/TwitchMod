package tv.twitch.android.shared.player.overlay;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import tv.twitch.android.mod.bridges.interfaces.IBottomPlayerControlOverlayViewDelegate;
import tv.twitch.android.mod.hooks.Controller;
import tv.twitch.android.mod.hooks.Jump;
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
        Controller.updateLockButtonState(this.lockButton);
    }

    private void setupLockButton(View view) { // TODO: __INJECT_METHOD
        this.lockButton = (ImageView) Controller.setupLockButton(view, this);
        updateLockButtonState();
    }

    public void setLockButtonVisible(boolean z) { // TODO: __INJECT_METHOD
        Controller.changeLockButtonVisibility(this.lockButton, z);
    }

    public void clickRefresh() { // TODO: __INJECT_METHOD
        if (mBottomPlayerControlListener != null) {
            mBottomPlayerControlListener.onRefreshClicked();
        }
    }


    private void setupRefreshButton(View view) { // TODO: __INJECT_METHOD
        this.refreshButton = (ImageView) Controller.setupRefreshButton(view, this);
        if (!Jump.shouldShowRefreshButton() && this.refreshButton != null) {
            this.refreshButton.setVisibility(View.GONE);
        }
    }

    private void setupUptime(View view) { // TODO: __INJECT_METHOD
        this.uptimeView = (StreamUptimeView) Controller.setupUptime(view, this);
        this.uptimeIcon = (ImageView) Controller.setupUptimeIcon(view, this);

        hideUptime();
    }

    public void showUptime(int seconds) { // TODO: __INJECT_METHOD
        if (!Jump.shouldShowStreamUptime())
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
            this.uptimeIcon.setVisibility(View.INVISIBLE);
        }

        if (this.uptimeView != null) {
            this.uptimeView.hide();
        }
    }
}
