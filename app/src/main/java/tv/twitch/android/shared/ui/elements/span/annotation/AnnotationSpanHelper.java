package tv.twitch.android.shared.ui.elements.span.annotation;


import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;

import java.util.Map;

public class AnnotationSpanHelper {
    private final Context context = null;

    /* ... */

    public final Spannable createAnnotatedSpannable(int i, Map<String, ? extends AnnotationSpanArgType> map, String... strArr) { // TODO: __REPLACE_METHOD
        CharSequence text = this.context.getText(i);
        if (text != null) {
            Spanned spanned = (text instanceof Spanned) ? (Spanned) text : SpannableString.valueOf(text);
            SpannableString formatSpannedString = formatSpannedString(spanned, strArr); // FIXME: (Spanned) text
            applyAnnotations(formatSpannedString, map);
            return formatSpannedString;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.text.Spanned");
    }

    public final Spannable createAnnotatedSpannable(int i, Map<String, ? extends AnnotationSpanArgType> map, int i2, String... strArr) { // TODO: __REPLACE_METHOD
        CharSequence quantityText = this.context.getResources().getQuantityText(i, i2);
        if (quantityText != null) {
            Spanned spanned = (quantityText instanceof Spanned) ? (Spanned) quantityText : SpannableString.valueOf(quantityText);
            SpannableString formatSpannedString = formatSpannedString(spanned, strArr); // FIXME: (Spanned) quantityText
            applyAnnotations(formatSpannedString, map);
            return formatSpannedString;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.text.Spanned");
    }

    private final SpannableString formatSpannedString(Spanned spanned, String[] strArr) {
        /* ... */

        return null;
    }

    private final SpannableString applyAnnotations(SpannableString spannableString, Map<String, ? extends AnnotationSpanArgType> map) {
        /* ... */

        return null;
    }

    /* ... */
}