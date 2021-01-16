package tv.twitch.android.shared.player.overlay.seekable;


import tv.twitch.android.mod.hooks.Hook;


public class PlayPauseFastSeekViewDelegate {
    /* ... */

    private void setTouchListenersForFastSeeking() {
        /* ... */

        int fastSeekRewind = Hook.getRewindSeek(); // TODO: __HOOK
        int fastSeekForward = Hook.getForwardSeek(); // TODO: __HOOK

        /* ... */
    }

    /* ... */
}
