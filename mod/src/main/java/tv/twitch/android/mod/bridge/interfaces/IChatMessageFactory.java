package tv.twitch.android.mod.bridge.interfaces;


import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.models.UrlDrawableCallback;


public interface IChatMessageFactory {
    CharSequence getSpannedEmote(String url, String emoteText, UrlDrawableCallback callback);

    CharSequence getSpannedBadge(String url, String badgeName, UrlDrawableCallback callback);

    TwitchAccountManager getAccountManager();
}
