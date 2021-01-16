package tv.twitch.android.shared.billing;


import tv.twitch.android.mod.hooks.Jump;


public class RxBillingClient {
    /* ... */

    public final boolean isAvailable() {
        if (Jump.isGoogleBillingDisabled()) // TODO: __INJECT_CODE
            return false;

        /* ... */

        return false;
    }

    /* ... */
}
