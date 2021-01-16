package tv.twitch.android.shared.player.overlay.seekable;


import tv.twitch.android.mod.hooks.General;


public class PlayPauseFastSeekViewDelegate {
    /* ... */

    private void setTouchListenersForFastSeeking() {
        /* ... */

        int fastSeekRewind = General.getRewindSeek(); // TODO: __HOOK
        int fastSeekForward = General.getForwardSeek(); // TODO: __HOOK

        /* ... */
    }

    /* ... */
}
