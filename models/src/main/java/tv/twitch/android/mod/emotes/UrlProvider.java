package tv.twitch.android.mod.emotes;


import tv.twitch.android.mod.models.preferences.ImageSize;


public interface UrlProvider {
    String getUrl(@ImageSize String size);
}
