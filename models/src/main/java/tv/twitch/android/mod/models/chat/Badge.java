package tv.twitch.android.mod.models.chat;


import tv.twitch.android.mod.emotes.UrlProvider;

public interface Badge {
    String getName();

    UrlProvider getUrlProvider();

    String[] getReplaces();
}