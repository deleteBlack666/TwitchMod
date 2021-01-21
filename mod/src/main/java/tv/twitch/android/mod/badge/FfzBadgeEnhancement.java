package tv.twitch.android.mod.badge;


import android.graphics.drawable.Drawable;

import tv.twitch.android.mod.models.UrlDrawableCallback;

import static android.graphics.PorterDuff.Mode.DST_OVER;


public class FfzBadgeEnhancement implements UrlDrawableCallback {
    private final int badgeColor;

    public FfzBadgeEnhancement(int color) {
        this.badgeColor = color;
    }

    @Override
    public Drawable onDrawableSet(Drawable drawable) {
        if (drawable == null)
            return null;

        return fixFfzBadge(drawable);
    }

    private Drawable fixFfzBadge(Drawable icon) {
        if (icon == null)
            return null;

        icon.setColorFilter(badgeColor, DST_OVER);

        return icon;
    }
}
