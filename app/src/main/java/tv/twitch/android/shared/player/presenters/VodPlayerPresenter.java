package tv.twitch.android.shared.player.presenters;


import tv.twitch.android.mod.hooks.HookJump;


public class VodPlayerPresenter {
    /* ... */

    public final void requestAd(Object iVideoAdManager, Object clientAdRequestMetadata, boolean z) {
        if (HookJump.isAdblockOn()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

    /* ... */
}
