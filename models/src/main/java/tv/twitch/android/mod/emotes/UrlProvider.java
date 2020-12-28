package tv.twitch.android.mod.emotes;


import tv.twitch.android.mod.models.preferences.EmoteSize;

public interface UrlProvider {
    String getUrl(@EmoteSize String size);
}
