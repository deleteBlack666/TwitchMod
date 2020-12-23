package tv.twitch.android.mod.utils;


import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.util.List;

import tv.twitch.android.core.adapters.RecyclerAdapterItem;
import tv.twitch.android.mod.bridges.interfaces.IChatTextViewItem;
import tv.twitch.android.shared.ui.elements.span.CenteredImageSpan;
import tv.twitch.android.mod.bridges.interfaces.IUrlDrawable;
import tv.twitch.android.mod.bridges.interfaces.IChatMessageItem;


public class GifHelper {
    public static void recycleItems(List<RecyclerAdapterItem> items) {
        if (items == null || items.size() == 0)
            return;

        for (Object o : items)
            recycleObject(o, true);
    }

    public static void recycleObject(Object item, boolean clear) {
        if (item instanceof IChatMessageItem) {
            recycleGifsInText(((IChatMessageItem) item).getSpanned(), clear);
        } else if (item instanceof IChatTextViewItem) {
            TextView tv = ((IChatTextViewItem) item).getTextView();
            if (tv != null) {
                recycleGifsInText(tv.getText(), clear);
                if (clear) {
                    tv.setText(null);
                }
            }
        } else if (item instanceof TextView) {
            recycleGifsInText(((TextView) item).getText(), clear);
            if (clear)
                ((TextView) item).setText(null);
        } else if (item instanceof CharSequence) {
            recycleGifsInText((CharSequence) item, clear);
        } else {
            if (item == null) {
                Logger.debug("item is null");
                return;
            }

            Logger.debug("class=" + item.getClass());
        }
    }

    public static void recycleGifsInText(CharSequence sequence, boolean clear) {
        if (TextUtils.isEmpty(sequence))
            return;

        Spanned message = (Spanned) sequence;

        CenteredImageSpan[] imageSpans = message.getSpans(0, message.length(), CenteredImageSpan.class);
        if (imageSpans.length == 0)
            return;

        for (CenteredImageSpan image: imageSpans) {
            if (image == null)
                continue;

            Drawable drawable = image.getImageDrawable();
            if (!(drawable instanceof IUrlDrawable))
                continue;

            IUrlDrawable drawableContainer = (IUrlDrawable) drawable;

            Drawable gifDrawable = drawableContainer.getDrawable();
            if (gifDrawable instanceof GifDrawable) {
                ((GifDrawable) gifDrawable).stop();
                if (clear) {
                    gifDrawable.setCallback(null);
                }
            }
        }
    }
}
