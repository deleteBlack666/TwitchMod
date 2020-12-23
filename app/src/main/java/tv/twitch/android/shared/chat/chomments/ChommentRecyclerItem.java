package tv.twitch.android.shared.chat.chomments;


import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import tv.twitch.android.mod.bridges.Hooks;
import tv.twitch.android.mod.bridges.interfaces.IChatTextViewItem;
import tv.twitch.android.models.chomments.ChommentModel;


public class ChommentRecyclerItem {
    private final Spanned msgSpan = null;
    private final ChommentModel chommentModel = null;

    /* ... */

    public ChommentRecyclerItem(FragmentActivity fragmentActivity, ChommentModel chommentModel2, Spanned spanned, Object function1, boolean z) {
        spanned = Hooks.addVodTimestampToMessages(spanned, chommentModel2); // TODO: __INJECT_CODE

        /* ... */
    }



    public static final class ChommentItemViewHolder extends RecyclerView.ViewHolder implements IChatTextViewItem { // TODO: __IMPLEMENT
        private TextView chommentTextView;
        /* ... */

        public ChommentItemViewHolder(View view) {
            super(view);

            /* ... */

            Hooks.setChatMessageFontSize(chommentTextView); // TODO: __INJECT_CODE
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
