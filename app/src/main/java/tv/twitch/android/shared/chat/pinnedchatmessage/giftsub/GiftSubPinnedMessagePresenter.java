package tv.twitch.android.shared.chat.pinnedchatmessage.giftsub;


import tv.twitch.android.mod.hooks.Jump;

public class GiftSubPinnedMessagePresenter {
    /* ... */

    public final void maybeShowGiftSubBanner(String str, int i) {
        if (Jump.shouldHideSystemMessages()) // TODO: __INJECT_CODE
            return;

        /* ... */
    }

    /* ... */
}