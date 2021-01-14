package tv.twitch.android.mod.badge;


import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;

import tv.twitch.android.mod.badge.fetchers.FfzBadgesFetcher;
import tv.twitch.android.mod.models.chat.Badge;
import tv.twitch.android.mod.models.chat.BadgeSet;
import tv.twitch.android.mod.util.Logger;


public class BadgeManager implements FfzBadgesFetcher.Callback {
    public static final BadgeManager INSTANCE = new BadgeManager();

    private final FfzBadgesFetcher mFfzFetcher;

    private BadgeSet mFfzBadgeSet;

    private BadgeManager() {
        mFfzFetcher = new FfzBadgesFetcher(this);
    }

    public void fetchBadges() {
        Logger.debug("Fetching badges...");
        mFfzFetcher.fetch();
    }

    @NonNull
    public Collection<Badge> getFfzBadges(Integer userId) {
        if (userId <= 0) {
            Logger.debug("userId  <= 0");
            return Collections.emptyList();
        }

        if (mFfzBadgeSet == null)
            return Collections.emptyList();

        return mFfzBadgeSet.getBadges(userId);
    }

    @Override
    public void onFfzBadgesParsed(FfzBadgeSet set) {
        if (set == null) {
            Logger.error("set is null");
            return;
        }

        mFfzBadgeSet = set;
    }
}
