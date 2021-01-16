package tv.twitch.android.feature.amazon.identity;


import tv.twitch.android.mod.hooks.HookJump;


public class AmazonIdentityPresenter {
    /* ... */

    public final boolean isUserEligibleToRegister(int userId, int userIdRegistered) {
        if (HookJump.isAdblockOn()) { // TODO: __INJECT_CODE
            return false;
        }

        /* ... */

        return true;
    }

    /* ... */
}
