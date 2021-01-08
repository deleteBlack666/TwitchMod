package tv.twitch.android.mod.emotes;


import android.text.TextUtils;

import java.util.HashMap;

import tv.twitch.android.mod.models.api.FfzUrls;
import tv.twitch.android.mod.models.preferences.ImageSize;


public class FfzUrlProvider implements UrlProvider {
    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public FfzUrlProvider(HashMap<String, String> urls) {
        this.mSmallEmoteUrl = urls.get("1x");
        this.mMediumEmoteUrl = urls.get("2x");
        this.mLargeEmoteUrl = urls.get("4x");
    }

    public FfzUrlProvider(FfzUrls ffzUrls) {
        this.mSmallEmoteUrl = ffzUrls.getSmall();
        this.mMediumEmoteUrl = ffzUrls.getMedium();
        this.mLargeEmoteUrl = ffzUrls.getLarge();
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
}
