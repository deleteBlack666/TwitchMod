package tv.twitch.android.feature.theatre;


import tv.twitch.android.mod.bridges.Hooks;


public class LandscapeChatLayoutController {
    /* ... */

    public final void setupForLandscapeDefault() {
        /* ... */

        // ViewExtensionsKt.setLargestScreenEdgePercentage(this.mLandscapeContainer, experience.shouldShowTabletUI(context) ? 20 : 30);
        int percent = Hooks.hookSetupForLandscapeDefaultChatScreenEdgePercentage(30); // TODO: __HOOK_ARG

        /* ... */
    }

    public final void setupForLandscapeSplitView() {
        /* ... */

        // ViewExtensionsKt.setLargestScreenEdgePercentage(this.mLandscapeContainer, 50);
        int percent = Hooks.hookSetupForLandscapeSplitViewChatScreenEdgePercentage(50); // TODO: __HOOK_ARG

        /* ... */
    }

    /* ... */
}