package tv.twitch.android.mod.bridge.interfaces;


import android.graphics.drawable.Drawable;


public interface IUrlDrawable {
    Drawable getDrawable();

    boolean isBadge();

    void setTwitchEmote(boolean z);

    boolean isTwitchEmote();
}
