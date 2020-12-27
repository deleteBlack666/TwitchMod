package tv.twitch.android.feature.amazon.identity;


import tv.twitch.android.mod.bridges.Hooks;

public class AmazonIdentityPresenter {
    /* ... */

    public final boolean isUserEligibleToRegister(int i, int i2) {
        if (Hooks.isAdblockOn()) // TODO: __INJECT_CODE
            return false;

        /* ... */

        return true;
    }

    /* ... */
}
