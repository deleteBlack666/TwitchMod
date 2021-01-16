package tv.twitch.android.shared.chat;


import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridge.interfaces.ILiveChatSource;
import tv.twitch.android.mod.hooks.HookJump;
import tv.twitch.android.shared.chat.events.ChatNoticeEvents;
import tv.twitch.android.shared.chat.messagefactory.MessageListAdapterBinder;


public class LiveChatSource implements ILiveChatSource { // TODO: __IMPLEMENT
    private final MessageListAdapterBinder messageListAdapterBinder = null;
    public final TwitchAccountManager accountManager = null;

    /* ... */

    public final void onUserNoticeReceived(ChatNoticeEvents noticeEvents) {
        if (noticeEvents instanceof ChatNoticeEvents.FirstTimeChatterNoticeEvent
                && !HookJump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */

        if (noticeEvents instanceof ChatNoticeEvents.SubscriptionNoticeEvent
                && !HookJump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */

        if (noticeEvents instanceof ChatNoticeEvents.RewardGiftNoticeEvent
                && !HookJump.shouldHideSystemMessages()) { // TODO: __JUMP_HOOK
            /* ... */
        }

        /* ... */
    }

    @Override
    public void addRecentMessage(String msg) { // TODO: __INJECT_METHOD
        this.messageListAdapterBinder.addRecentMessage(msg);
    }
}