package tv.twitch.android.mod.emotes;


import java.util.HashMap;


public final class UrlProviderFactory {
    public static UrlProvider getFfzUrlProvider(HashMap<String, String> urls) {
        return new FfzUrlProvider(urls);
    }

    public static UrlProvider getBttvUrlProvider(String emoteId) {
        return new BttvUrlProvider(emoteId);
    }
}
