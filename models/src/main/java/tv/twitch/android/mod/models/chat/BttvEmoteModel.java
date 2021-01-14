package tv.twitch.android.mod.models.chat;


import androidx.annotation.NonNull;

import tv.twitch.android.mod.emote.UrlProvider;


public final class BttvEmoteModel implements Emote {
    private final String mCode;
    private final String mEmoteId;
    private final boolean bIsGif;

    private final UrlProvider mUrlProvider;

    public BttvEmoteModel(String code, String id, boolean isGif, UrlProvider urlProvider) {
        this.mCode = code;
        this.mEmoteId = id;
        this.bIsGif = isGif;
        this.mUrlProvider = urlProvider;
    }

    @NonNull
    @Override
    public String getCode() {
        return mCode;
    }


    @Override
    public boolean isGif() {
        return bIsGif;
    }

    @Override
    public String getEmoteId() {
        return mEmoteId;
    }

    @Override
    public UrlProvider getUrlProvider() {
        return mUrlProvider;
    }

    @Override
    public String toString() {
        return "BttvEmoteModel{" +
                "mCode='" + mCode + '\'' +
                ", mEmoteId='" + mEmoteId + '\'' +
                ", bIsGif=" + bIsGif +
                ", mUrlProvider=" + mUrlProvider +
                '}';
    }
}
