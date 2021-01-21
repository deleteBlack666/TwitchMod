package tv.twitch.android.mod.models.chat;


import android.graphics.Color;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import tv.twitch.android.mod.emote.UrlProvider;


public final class FfzBadge implements Badge {
    private final String mName;
    private final UrlProvider mUrlProvider;
    private final String mReplaces;
    private final String mId;
    private final int mColor;


    public FfzBadge(String name, String id, UrlProvider provider, @Nullable String replaces, String color) {
        mName = name;
        mId = id;
        mUrlProvider = provider;
        mReplaces = replaces;
        mColor = TextUtils.isEmpty(color) ? Color.TRANSPARENT : Color.parseColor(color);
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public UrlProvider getUrlProvider() {
        return mUrlProvider;
    }

    public String getReplaces() {
        return mReplaces;
    }

    public int getColor() {
        return mColor;
    }

    @Override
    public String toString() {
        return "FfzBadge{" +
                "mName='" + mName + '\'' +
                ", mUrlProvider=" + mUrlProvider +
                ", mReplaces='" + mReplaces + '\'' +
                ", mId='" + mId + '\'' +
                ", mColor=" + mColor +
                '}';
    }
}

