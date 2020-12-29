package tv.twitch.android.shared.chat.messagefactory.adapteritem;


import android.content.ContextWrapper;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import tv.twitch.android.core.mvp.viewdelegate.EventDispatcher;
import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.shared.chat.adapter.item.ChatAdapterItem;
import tv.twitch.android.shared.chat.util.ChatItemClickEvent;


public class UserNoticeRecyclerItem implements ChatAdapterItem {
    /* ... */

    public UserNoticeRecyclerItem(ContextWrapper contextWrapper, int i, int i2, Spanned spanned, UserNoticeConfig userNoticeConfig, Spanned messageSpan, EventDispatcher<ChatItemClickEvent> eventDispatcher, String messageId, String rawMessage, boolean hasBeenDeleted) {

    }

    public static abstract class UserNoticeConfig {
        private UserNoticeConfig(int i, Integer num) {/* ... */}

        public static final class GenericNotice extends UserNoticeConfig {
            public GenericNotice() {
                super(0, null);
            }
        }
    }
    public static final class UserNoticeViewHolder extends RecyclerView.ViewHolder {
        private final TextView chatMessage = null;
        private final TextView systemMessage = null;

        /* ... */

        public UserNoticeViewHolder(View view) {
            super(view);

            General.setChatMessageFontSize(chatMessage); // TODO: __INJECT_CODE
            General.setChatMessageFontSize(systemMessage); // TODO: __INJECT_CODE
            /* ... */
        }

        /* ... */
    }

    /* ... */
}
