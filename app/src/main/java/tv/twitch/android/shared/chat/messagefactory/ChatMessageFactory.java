package tv.twitch.android.shared.chat.messagefactory;


import android.content.ContextWrapper;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;

import java.util.concurrent.TimeUnit;

import tv.twitch.android.adapters.social.MessageRecyclerItem;
import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.mod.hooks.Jump;
import tv.twitch.android.models.webview.WebViewSource;
import tv.twitch.android.shared.chat.ChatMessageInterface;
import tv.twitch.android.shared.chat.adapter.item.ChatAdapterItem;
import tv.twitch.android.shared.chat.chatsource.IClickableUsernameSpanListener;
import tv.twitch.android.shared.chat.messagefactory.adapteritem.UserNoticeRecyclerItem;
import tv.twitch.android.shared.chat.messageinput.emotes.RecentEmotesManager;
import tv.twitch.android.shared.chat.tracking.ChatFiltersSettings;
import tv.twitch.android.shared.chat.util.ChatItemClickEvent;
import tv.twitch.android.shared.ui.elements.span.CenteredImageSpan;
import tv.twitch.android.shared.ui.elements.span.TwitchUrlSpanClickListener;
import tv.twitch.android.shared.ui.elements.span.MediaSpan$Type;
import tv.twitch.android.shared.chat.UrlImageClickableProvider;
import tv.twitch.android.mod.bridge.interfaces.IChatMessageFactory;
import tv.twitch.android.shared.ui.elements.span.UrlDrawable;
import tv.twitch.chat.ChatMessageInfo;


public class ChatMessageFactory implements IChatMessageFactory { // TODO: __IMPLEMENT
    private ContextWrapper context;

    private TwitchAccountManager accountManager;
    private RecentEmotesManager recentEmotesManager;

    /* ... */

    private final CharSequence imageSpannable(String str, MediaSpan$Type mediaSpan$Type, String str2, UrlImageClickableProvider urlImageClickableProvider, boolean z) {
        return null;
    }

    private final CharSequence usernameSpannable(ChatMessageInterface chatMessageInterface, int color, IClickableUsernameSpanListener iClickableUsernameSpanListener, boolean z, String str, String str2) {
        color = General.hookUsernameSpanColor(color); // TODO: __HOOK_PARAM // TODO: __FIX

        /* ... */
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    public final Object createChatMessageItem(ChatMessageInfo chatMessageInfo, boolean z, boolean z2, boolean z3, int i, int i2, IClickableUsernameSpanListener iClickableUsernameSpanListener, TwitchUrlSpanClickListener twitchUrlSpanClickListener, WebViewSource webViewSource, String str, boolean z4, ChatFiltersSettings chatFiltersSettings, EventDispatcher<ChatItemClickEvent> eventDispatcher) {
        /* ... */

        MessageRecyclerItem ret = null;

        /* ... */

        ret.setShouldHighlightBackground(General.shouldHighlightMessage(chatMessageInfo, accountManager)); // TODO: __INJECT_CODE

        return ret;
    }

    private final ChatMessageSpanGroup createChatMessageSpanGroup(ChatMessageInterface chatMessageInterface, boolean z, boolean z2, boolean z3, int userId, int channelId, IClickableUsernameSpanListener iClickableUsernameSpanListener, TwitchUrlSpanClickListener twitchUrlSpanClickListener, WebViewSource webViewSource, String str, boolean z4, ChatFiltersSettings chatFiltersSettings, Integer num, EventDispatcher<ChatItemClickEvent> eventDispatcher) {
        /* ... */

        SpannedString parseChatMessageTokens$default = new SpannedString("KEKW");
        SpannedString generateBadges = new SpannedString("LULW");
        parseChatMessageTokens$default = General.hookChatMessage(this, chatMessageInterface, parseChatMessageTokens$default, channelId); // TODO: __HOOK
        generateBadges = General.hookChatMessageBadges(this, chatMessageInterface, generateBadges); // TODO: __HOOK

        /* ... */

        return null;
    }

    /* ... */

    @Override
    public CharSequence getSpannedEmote(String url, String emoteText) { // TODO: __INJECT_METHOD
        UrlDrawable urlDrawable = new UrlDrawable(url, MediaSpan$Type.Emote);
        urlDrawable.setTwitchEmote(false);
        urlDrawable.setShouldWide(Jump.isWideEmotesEnabled());

        SpannableString spannableString = new SpannableString(emoteText);
        spannableString.setSpan(new CenteredImageSpan(urlDrawable, null), 0, emoteText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public CharSequence getSpannedBadge(String url, String badgeName) { // TODO: __INJECT_METHOD
        UrlDrawable urlDrawable = new UrlDrawable(url, MediaSpan$Type.Badge);

        SpannableString spannableString = new SpannableString(badgeName + " ");
        spannableString.setSpan(new CenteredImageSpan(urlDrawable, null), 0, badgeName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public TwitchAccountManager getAccountManager() { // TODO: __INJECT_METHOD
        return accountManager;
    }

    public ChatAdapterItem createRecentMessageItem(String line) { // TODO: __INJECT_METHOD
        UserNoticeRecyclerItem.UserNoticeConfig.GenericNotice genericNotice = new UserNoticeRecyclerItem.UserNoticeConfig.GenericNotice();

        return new UserNoticeRecyclerItem(this.context, 0, (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), null, genericNotice, new SpannableStringBuilder(line), null, null, line, false);
    }
}