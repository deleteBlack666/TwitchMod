package tv.twitch.android.shared.ads;


import tv.twitch.android.mod.hooks.Jump;

public class AdsPlayerPresenter {
    /* ... */

    private final void requestAd(Object iVideoAdManager, Object clientAdRequestMetadata, boolean z, boolean z2) {
        if (Jump.isAdblockOn()) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

    /* ... */
}
