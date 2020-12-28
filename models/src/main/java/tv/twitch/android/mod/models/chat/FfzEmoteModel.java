package tv.twitch.android.mod.models.chat;


import androidx.annotation.NonNull;

import tv.twitch.android.mod.emotes.UrlProvider;


public class FfzEmoteModel implements Emote {
    private final String mCode;
    private final String mEmoteId;
    
    private final UrlProvider mUrlProvide;

    public FfzEmoteModel(String code, String id, UrlProvider urlProvider) {
        this.mCode = code;
        this.mEmoteId = id;
        this.mUrlProvide = urlProvider;
    }

    @NonNull
    @Override
    public String getCode() {
        return mCode;
    }


    @Override
    public boolean isGif() {
        return false;
    }

    @Override
    public String getEmoteId() {
        return mEmoteId;
    }

    @Override
    public UrlProvider getUrlProvider() {
        return mUrlProvide;
    }

    @NonNull
    @Override
    public String toString() {
        return "FfzEmoteModel{" +
                "mCode='" + mCode + '\'' +
                ", mEmoteId='" + mEmoteId + '\'' +
                ", mUrlProvide=" + mUrlProvide +
                '}';
    }
}
