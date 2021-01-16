package tv.twitch.android.shared.subscriptions.purchasers;


import android.content.Context;

import tv.twitch.android.mod.hooks.HookJump;


public class GooglePlaySubscriptionPurchaser {
    /* ... */

    public boolean isAvailable(Context context) {
        if (HookJump.isGoogleBillingDisabled()) // TODO: __INJECT_CODE
            return false;

        /* ... */

        return false;
    }

    /* ... */
}
