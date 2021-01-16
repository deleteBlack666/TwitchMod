package tv.twitch.android.shared.chat.pinnedchatmessage.giftsub;


import tv.twitch.android.mod.hooks.HookJump;


public class GiftSubPinnedMessagePresenter {
    /* ... */

    public final void maybeShowGiftSubBanner(String str, int i) {
        if (HookJump.shouldHideSystemMessages()) // TODO: __INJECT_CODE
            return;

        /* ... */
    }

    /* ... */
}