package tv.twitch.android.mod.models.chat;


import androidx.annotation.Nullable;

import java.util.Arrays;

import tv.twitch.android.mod.emote.UrlProvider;


public final class FfzBadge implements Badge {
    private final String mName;
    private final UrlProvider mUrlProvider;
    private final String[] mReplaces;


    public FfzBadge(String name, UrlProvider provider, @Nullable String replaces) {
        mName = name;
        mUrlProvider = provider;
        mReplaces = replaces == null ? new String[0] : replaces.split(", ");
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public UrlProvider getUrlProvider() {
        return mUrlProvider;
    }

    @Override
    public String[] getReplaces() {
        return mReplaces;
    }

    @Override
    public String toString() {
        return "FfzBadge{" +
                "mName='" + mName + '\'' +
                ", mUrlProvider=" + mUrlProvider +
                ", mReplaces=" + Arrays.toString(mReplaces) +
                '}';
    }
}

