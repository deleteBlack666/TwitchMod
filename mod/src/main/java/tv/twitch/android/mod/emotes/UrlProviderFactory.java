package tv.twitch.android.mod.emotes;


import java.util.HashMap;

import tv.twitch.android.mod.models.api.FfzUrls;


public final class UrlProviderFactory {
    public static UrlProvider getFfzUrlProvider(HashMap<String, String> urls) {
        return new FfzUrlProvider(urls);
    }

    public static UrlProvider getFfzUrlProvider(FfzUrls ffzUrls) {
        return new FfzUrlProvider(ffzUrls);
    }

    public static UrlProvider getBttvUrlProvider(String emoteId) {
        return new BttvUrlProvider(emoteId);
    }
}
