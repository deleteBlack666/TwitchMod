package tv.twitch.android.player.ads;


import tv.twitch.android.mod.hooks.Jump;


public class VideoAdManager {
    /* ... */

    public void requestAds(Object vASTAdPosition, Object videoAdRequestInfo2) {
        if (Jump.isAdblockOn()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

}
