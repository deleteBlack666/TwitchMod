package tv.twitch.android.mod.emotes;


import java.util.HashMap;

import tv.twitch.android.mod.models.preferences.EmoteSize;


public class FfzUrlProvider implements UrlProvider {
    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public FfzUrlProvider(HashMap<String, String> urls) {
        this.mSmallEmoteUrl = urls.get("1x");
        this.mMediumEmoteUrl = urls.get("2x");
        this.mLargeEmoteUrl = urls.get("4x");
    }

    @Override
    public String getUrl(@EmoteSize String size) {
        switch (size) {
            case EmoteSize.LARGE:
                if (mLargeEmoteUrl != null)
                    return mLargeEmoteUrl;
            default:
            case EmoteSize.MEDIUM:
                if (mMediumEmoteUrl != null)
                    return mMediumEmoteUrl;
            case EmoteSize.SMALL:
                if (mSmallEmoteUrl != null)
                    return mSmallEmoteUrl;
        }

        return null;
    }
}
