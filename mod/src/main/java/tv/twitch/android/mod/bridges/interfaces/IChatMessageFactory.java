package tv.twitch.android.mod.bridges.interfaces;


import tv.twitch.android.core.user.TwitchAccountManager;


public interface IChatMessageFactory {
    CharSequence getSpannedEmote(String url, String emoteText);

    CharSequence getSpannedBadge(String url, String badgeName);

    TwitchAccountManager getAccountManager();
}
