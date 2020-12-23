package tv.twitch.android.mod.models;


import androidx.annotation.NonNull;

import tv.twitch.android.mod.models.preferences.EmoteSize;


public final class BttvEmoteModel implements Emote {
    private static final String BASE_URL = "https://cdn.betterttv.net/emote/";

    private final String mCode;
    private final String mEmoteId;
    private final boolean bIsGif;

    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public BttvEmoteModel(String code, String id, boolean isGif) {
        this.mCode = code;
        this.mEmoteId = id;
        this.bIsGif = isGif;
        this.mSmallEmoteUrl = getUrl("1x", id);
        this.mMediumEmoteUrl = getUrl("2x", id);
        this.mLargeEmoteUrl = getUrl("3x", id);
    }

    @NonNull
    @Override
    public String getCode() {
        return mCode;
    }

    @Override
    public String getUrl(@EmoteSize String size) {
        switch (size) {
            case EmoteSize.LARGE:
                return mLargeEmoteUrl;
            default:
            case EmoteSize.MEDIUM:
                return mMediumEmoteUrl;
            case EmoteSize.SMALL:
                return mSmallEmoteUrl;
        }
    }

    @Override
    public boolean isGif() {
        return bIsGif;
    }

    @Override
    public String getEmoteId() {
        return mEmoteId;
    }

    private static String getUrl(String size, String id) {
        return BASE_URL + id + "/" + size;
    }

    @Override
    public String toString() {
        return "BttvEmoteModel{" +
                "mCode='" + mCode + '\'' +
                ", mEmoteId='" + mEmoteId + '\'' +
                ", bIsGif=" + bIsGif +
                ", mSmallEmoteUrl='" + mSmallEmoteUrl + '\'' +
                ", mMediumEmoteUrl='" + mMediumEmoteUrl + '\'' +
                ", mLargeEmoteUrl='" + mLargeEmoteUrl + '\'' +
                '}';
    }
}
