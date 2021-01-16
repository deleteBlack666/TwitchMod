package tv.twitch.android.shared.preferences;


import tv.twitch.android.mod.hooks.HookJump;


public class VideoDebugConfig {
    /* ... */

    public final boolean shouldShowVideoDebugPanel() { // TODO: __REPLACE_METHOD
        return HookJump.shouldShowStatButton();
    }

    /* ... */
}