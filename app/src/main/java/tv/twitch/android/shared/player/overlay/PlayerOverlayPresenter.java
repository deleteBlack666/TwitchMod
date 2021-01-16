package tv.twitch.android.shared.player.overlay;


import tv.twitch.android.mod.hooks.Hook;
import tv.twitch.android.models.channel.ChannelModel;
import tv.twitch.android.models.streams.StreamModel;


public class PlayerOverlayPresenter {
    private PlayerOverlayViewDelegate viewDelegate;

    /* ... */

    public final void bindPreviewTheatreOverlay() {
        PlayerOverlayViewDelegate playerOverlayViewDelegate = null;
        if (playerOverlayViewDelegate != null) {
            /* ... */

            playerOverlayViewDelegate.setLockButtonVisible(false); // TODO: __INJECT_CODE
            updateStreamUptime(null); // TODO: __INJECT_CODE

            /* ... */
        }

        /* ... */
    }

    public final void bindStream(StreamModel streamModel2, String str) {
        updateStreamUptime(streamModel2); // TODO: __INJECT_CODE

        /* ... */
    }

    public final void bindHostedStream(ChannelModel channelModel2, StreamModel streamModel2) {
        updateStreamUptime(streamModel2); // TODO: __INJECT_CODE

        /* ... */
    }

    private void updateStreamUptime(StreamModel streamModel2) { // TODO: __INJECT_METHOD
        this.viewDelegate.showUptime(Hook.calcSteamUptime(streamModel2));
    }


        /* ... */
}
