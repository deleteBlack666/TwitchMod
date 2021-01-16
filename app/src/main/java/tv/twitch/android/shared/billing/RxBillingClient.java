package tv.twitch.android.shared.billing;


import tv.twitch.android.mod.hooks.HookJump;


public class RxBillingClient {
    /* ... */

    public final boolean isAvailable() {
        if (HookJump.isGoogleBillingDisabled()) // TODO: __INJECT_CODE
            return false;

        /* ... */

        return false;
    }

    /* ... */
}
