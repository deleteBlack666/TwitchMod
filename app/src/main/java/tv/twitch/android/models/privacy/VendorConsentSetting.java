package tv.twitch.android.models.privacy;


import tv.twitch.android.mod.hooks.Jump;

public class VendorConsentSetting {
    /* ... */

    public final VendorConsentStatus getConsentStatus() {
        if (Jump.isAdblockOn()) { // TODO: __INJECT_CODE
            return VendorConsentStatus.Denied;
        }

        /* ... */

        return null;
    }

    /* ... */
}
