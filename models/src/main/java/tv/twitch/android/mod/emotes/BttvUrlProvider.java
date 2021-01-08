package tv.twitch.android.mod.emotes;


import tv.twitch.android.mod.models.preferences.ImageSize;


public class BttvUrlProvider implements UrlProvider {
    private static final String BASE_URL = "https://cdn.betterttv.net/emote/";

    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public BttvUrlProvider(String emoteId) {
        this.mSmallEmoteUrl = getUrl("1x", emoteId);
        this.mMediumEmoteUrl = getUrl("2x", emoteId);
        this.mLargeEmoteUrl = getUrl("3x", emoteId);
    }

    @Override
    public String getUrl(@ImageSize String size) {
        switch (size) {
            case ImageSize.LARGE:
                return mLargeEmoteUrl;
            default:
            case ImageSize.MEDIUM:
                return mMediumEmoteUrl;
            case ImageSize.SMALL:
                return mSmallEmoteUrl;
        }
    }

    private static String getUrl(String size, String emoteId) {
        return BASE_URL + emoteId + "/" + size;
    }
}
