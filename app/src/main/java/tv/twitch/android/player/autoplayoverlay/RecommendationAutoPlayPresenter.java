package tv.twitch.android.player.autoplayoverlay;


import tv.twitch.android.mod.hooks.HookJump;
import tv.twitch.android.models.Playable;


public class RecommendationAutoPlayPresenter<T extends Playable> {
    /* ... */

    public final void prepareRecommendationForCurrentModel(T t) {
        /* ... */

        if (!HookJump.isAutoplayDisabled()) // TODO: __JUMP_HOOK
            // ISubscriptionHelper....
            return;

        /* ... */
    }

    /* ... */
}
