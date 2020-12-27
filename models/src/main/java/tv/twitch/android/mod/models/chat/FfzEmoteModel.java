package tv.twitch.android.mod.models.chat;


import androidx.annotation.NonNull;

import java.util.HashMap;

import tv.twitch.android.mod.models.preferences.EmoteSize;


public class FfzEmoteModel implements Emote {
    private final String mCode;
    private final String mEmoteId;
    
    private final String mSmallEmoteUrl;
    private final String mMediumEmoteUrl;
    private final String mLargeEmoteUrl;

    public FfzEmoteModel(String code, String id, HashMap<String, String> urls) {
        this.mCode = code;
        this.mEmoteId = id;
        this.mSmallEmoteUrl = urls.get("1x");
        this.mMediumEmoteUrl = urls.get("2x");
        this.mLargeEmoteUrl = urls.get("4x");
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

        return "";
    }

    @Override
    public boolean isGif() {
        return false;
    }

    @Override
    public String getEmoteId() {
        return mEmoteId;
    }

    @NonNull
    @Override
    public String toString() {
        return "FfzEmoteModel{" +
                "mCode='" + mCode + '\'' +
                ", mEmoteId='" + mEmoteId + '\'' +
                ", mSmallEmoteUrl='" + mSmallEmoteUrl + '\'' +
                ", mMediumEmoteUrl='" + mMediumEmoteUrl + '\'' +
                ", mLargeEmoteUrl='" + mLargeEmoteUrl + '\'' +
                '}';
    }
}
