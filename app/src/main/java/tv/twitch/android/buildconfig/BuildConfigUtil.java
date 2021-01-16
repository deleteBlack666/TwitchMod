package tv.twitch.android.buildconfig;


import tv.twitch.android.mod.hooks.Jump;


public class BuildConfigUtil {
    public static final BuildConfigUtil INSTANCE = new BuildConfigUtil();

    /* ... */

    public final boolean isAlpha() { // TODO: __REPLACE_METHOD
        return Jump.inDevMode();
    }

    public final boolean isDebugConfigEnabled() { // TODO: __REPLACE_METHOD
        return Jump.inDevMode();
    }
    
    public final boolean shouldShowDebugOptions(boolean z) { // TODO: __REPLACE_METHOD
        return Jump.inDevMode();
    }

    /* ... */
}
