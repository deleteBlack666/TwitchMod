package tv.twitch.android.shared.chat.chomments;


import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.mod.bridge.interfaces.IChatMessageItem;
import tv.twitch.android.mod.bridge.interfaces.IChatTextViewItem;
import tv.twitch.android.models.chomments.ChommentModel;


public class ChommentRecyclerItem implements IChatMessageItem { // TODO: __IMPLEMENT
    private final Spanned msgSpan = null;
    private final ChommentModel chommentModel = null;

    /* ... */

    public ChommentRecyclerItem(FragmentActivity fragmentActivity, ChommentModel chommentModel2, Spanned spanned, Object function1, boolean z) {
        spanned = General.addVodTimestampToMessages(spanned, chommentModel2); // TODO: __INJECT_CODE

        /* ... */
    }

    @Override
    public CharSequence getSpanned() { // TODO: __INJECT_CODE
        return msgSpan;
    }


    public static final class ChommentItemViewHolder extends RecyclerView.ViewHolder implements IChatTextViewItem { // TODO: __IMPLEMENT
        private TextView chommentTextView;
        /* ... */

        public ChommentItemViewHolder(View view) {
            super(view);

            /* ... */

            General.setChatMessageFontSize(chommentTextView); // TODO: __INJECT_CODE
        }

        public final TextView getChommentTextView() {
            return null;
        }

        @Override
        public TextView getTextView() { // TODO: __INJECT_METHOD
            return getChommentTextView();
        }

        /* ... */
    }

    /* ... */
}
