package tv.twitch.android.shared.ads;


import tv.twitch.android.mod.hooks.HookJump;


public class AdsPlayerPresenter {
    /* ... */

    private final void requestAd(Object iVideoAdManager, Object clientAdRequestMetadata, boolean z, boolean z2) {
        if (HookJump.isAdblockOn()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

    /* ... */
}
