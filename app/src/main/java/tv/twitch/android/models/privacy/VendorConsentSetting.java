package tv.twitch.android.models.privacy;


import tv.twitch.android.mod.hooks.HookJump;


public class VendorConsentSetting {
    /* ... */

    public final VendorConsentStatus getConsentStatus() {
        if (HookJump.isAdblockOn()) { // TODO: __INJECT_CODE
            return VendorConsentStatus.Denied;
        }

        /* ... */

        return null;
    }

    /* ... */
}
