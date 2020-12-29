package tv.twitch.android.feature.amazon.identity;


import tv.twitch.android.mod.hooks.Jump;

public class AmazonIdentityPresenter {
    /* ... */

    public final boolean isUserEligibleToRegister(int i, int i2) {
        if (Jump.isAdblockOn()) { // TODO: __INJECT_CODE
            return false;
        }

        /* ... */

        return true;
    }

    /* ... */
}
