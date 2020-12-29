package tv.twitch.android.adapters.social;


import android.content.Context;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.subjects.PublishSubject;
import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.mod.bridges.interfaces.IChatMessageItem;
import tv.twitch.android.mod.bridges.interfaces.IChatTextViewItem;
import tv.twitch.android.shared.chat.adapter.SystemMessageType;
import tv.twitch.android.shared.chat.adapter.item.ChatAdapterItem;
import tv.twitch.android.shared.chat.adapter.item.ChatMessageClickedEvents;
import tv.twitch.android.shared.chat.util.ChatItemClickEvent;
import tv.twitch.android.shared.chat.util.ChatUtil;


public class MessageRecyclerItem implements ChatAdapterItem, IChatMessageItem { // TODO: __IMPLEMENT
    private boolean hasModAccess;
    private Spanned message;
    private PublishSubject<ChatMessageClickedEvents> messageClickSubject;
    private final Context context = null;

    public final String rawMessage = null;

    private boolean shouldHighlightBackground; // TODO: __INJECT_FIELD

    /* ... */

    public void bindToViewHolder(RecyclerView.ViewHolder viewHolder) {
        /* ... */

        ChatMessageViewHolder chatMessageViewHolder = (ChatMessageViewHolder) viewHolder;
        if (chatMessageViewHolder != null) {
            /* ... */

            maybeHighlightMessage(chatMessageViewHolder); // TODO: __INJECT_CODE
        }

    }

    public void setShouldHighlightBackground(boolean z) { // TODO: __INJECT_METHOD
        shouldHighlightBackground = z;
    }

    private void maybeHighlightMessage(ChatMessageViewHolder holder) { // TODO: __INJECT_METHOD
        if (holder == null) {
            return;
        }

        General.highlightView(holder.itemView, shouldHighlightBackground);
    }

    public MessageRecyclerItem(Context context2, String str, int authorUserId, String str2, String str3, int i2, Spanned message, SystemMessageType systemMessageType, float f, int i3, float f2, boolean z, boolean z2, String str4, EventDispatcher<ChatItemClickEvent> eventDispatcher) {
        message = General.addTimestampToMessage(message, authorUserId); // TODO: __HOOK_PARAM

        /* ... */
    }

    public void markAsDeleted() {
        ChatUtil.Companion companion = ChatUtil.Companion;

        /* ... */

        this.message = General.hookMarkAsDeleted(companion, this.message, this.context, this.messageClickSubject, this.hasModAccess); // TODO: __REPLACE_CODE
    }

    @Override
    public CharSequence getSpanned() { // TODO: __INJECT_METHOD
        return message;
    }


    public static final class ChatMessageViewHolder extends RecyclerView.ViewHolder implements IChatTextViewItem { // TODO: __IMPLEMENT
        private TextView messageTextView;

        /* ... */

        public ChatMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            /* ... */

            General.setChatMessageFontSize(messageTextView); // TODO: __INJECT_CODE
        }

        public final TextView getMessageTextView() {
            return null;
        }

        @Override
        public TextView getTextView() { // TODO: __INJECT_METHOD
            return getMessageTextView();
        }

        /* ... */
    }

    /* ... */
}