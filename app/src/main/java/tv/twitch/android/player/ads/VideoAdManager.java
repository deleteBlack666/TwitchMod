package tv.twitch.android.player.ads;


import tv.twitch.android.mod.hooks.HookJump;


public class VideoAdManager {
    /* ... */

    public void requestAds(Object vASTAdPosition, Object videoAdRequestInfo2) {
        if (HookJump.isAdblockOn()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

}
