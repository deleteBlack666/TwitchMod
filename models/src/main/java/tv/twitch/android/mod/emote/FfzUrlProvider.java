package tv.twitch.android.mod.emote;


import android.text.TextUtils;

import java.util.HashMap;

import tv.twitch.android.mod.models.api.FfzUrls;
import tv.twitch.android.mod.models.preferences.ImageSize;


public class FfzUrlProvider implements UrlProvider {
    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public FfzUrlProvider(HashMap<String, String> urls) {
        this.mSmallEmoteUrl = formatUrl(urls.get("1x"));
        this.mMediumEmoteUrl = formatUrl(urls.get("2x"));
        this.mLargeEmoteUrl = formatUrl(urls.get("4x"));
    }

    public FfzUrlProvider(FfzUrls ffzUrls) {
        this.mSmallEmoteUrl = formatUrl(ffzUrls.getSmall());
        this.mMediumEmoteUrl = formatUrl(ffzUrls.getMedium());
        this.mLargeEmoteUrl = formatUrl(ffzUrls.getLarge());
    }

    @Override
    public String getUrl(@ImageSize String size) {
        switch (size) {
            case ImageSize.LARGE:
                if (!TextUtils.isEmpty(mLargeEmoteUrl))
                    return mLargeEmoteUrl;
            default:
            case ImageSize.MEDIUM:
                if (!TextUtils.isEmpty(mMediumEmoteUrl))
                    return mMediumEmoteUrl;
            case ImageSize.SMALL:
                if (!TextUtils.isEmpty(mSmallEmoteUrl))
                    return mSmallEmoteUrl;
        }

        return null;
    }

    private static String formatUrl(String url) {
        if (url == null)
            return null;

        if (url.startsWith("//"))
            return "https:" + url;

        return url;
    }
}
