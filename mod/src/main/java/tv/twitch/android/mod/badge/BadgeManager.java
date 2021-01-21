package tv.twitch.android.mod.badge;


import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;

import tv.twitch.android.mod.badge.fetchers.FfzBadgesFetcher;
import tv.twitch.android.mod.core.fetcher.DonatorsFetcher;
import tv.twitch.android.mod.models.chat.Badge;
import tv.twitch.android.mod.models.chat.BadgeSet;
import tv.twitch.android.mod.util.Logger;


public class BadgeManager implements FfzBadgesFetcher.Callback, DonatorsFetcher.Callback {
    public static final BadgeManager INSTANCE = new BadgeManager();

    private final FfzBadgesFetcher mFfzFetcher;
    private final DonatorsFetcher mDonatorsFetcher;

    private BadgeSet mFfzBadgeSet;
    private BadgeSet mDonatosBadgeSet;

    private BadgeManager() {
        mFfzFetcher = new FfzBadgesFetcher(this);
        mDonatorsFetcher = new DonatorsFetcher(this);
    }

    public void fetchBadges() {
        Logger.debug("Fetching badges...");
        mFfzFetcher.fetch();
        mDonatorsFetcher.fetch();
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

    @NonNull
    public Collection<Badge> getDonatorsBadges(Integer userId) {
        if (userId <= 0) {
            Logger.debug("userId  <= 0");
            return Collections.emptyList();
        }

        if (mDonatosBadgeSet == null)
            return Collections.emptyList();

        return mDonatosBadgeSet.getBadges(userId);
    }

    @Override
    public void onFfzBadgesParsed(BadgeSetImpl set) {
        if (set == null) {
            Logger.error("set is null");
            return;
        }

        mFfzBadgeSet = set;
    }

    @Override
    public void onDonatorsFetched(BadgeSet set) {
        if (set == null) {
            Logger.error("set is null");
            return;
        }

        mDonatosBadgeSet = set;
    }
}
