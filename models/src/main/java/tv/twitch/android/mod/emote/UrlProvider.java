package tv.twitch.android.mod.emote;


import tv.twitch.android.mod.models.preferences.ImageSize;


public interface UrlProvider {
    String getUrl(@ImageSize String size);
}
