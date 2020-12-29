package tv.twitch.android.shared.chat;


import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridges.interfaces.ILiveChatSource;
import tv.twitch.android.mod.hooks.Jump;
import tv.twitch.android.shared.chat.events.ChatNoticeEvents;
import tv.twitch.android.shared.chat.messagefactory.MessageListAdapterBinder;


public class LiveChatSource implements ILiveChatSource { // TODO: __IMPLEMENT
    private final MessageListAdapterBinder messageListAdapterBinder = null;
    public final TwitchAccountManager accountManager = null;

    /* ... */

    public final void onUserNoticeReceived(ChatNoticeEvents noticeEvents) {
        if (noticeEvents instanceof ChatNoticeEvents.FirstTimeChatterNoticeEvent
                && !Jump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */

        if (noticeEvents instanceof ChatNoticeEvents.SubscriptionNoticeEvent
                && !Jump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */

        if (noticeEvents instanceof ChatNoticeEvents.RewardGiftNoticeEvent
                && !Jump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */
    }

    public final void addSystemMessage(String str, boolean z, String str2) {
        /* ... */

        return;
    }

    @Override
    public void addRecentMessage(String line) { // TODO: __INJECT_METHOD
        this.messageListAdapterBinder.addRecentMessage(line);
    }
}