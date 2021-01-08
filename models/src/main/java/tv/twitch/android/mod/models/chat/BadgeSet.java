package tv.twitch.android.mod.models.chat;


import androidx.annotation.NonNull;

import java.util.Collection;


public interface BadgeSet {
    @NonNull
    Collection<Badge> getBadges(Integer id);

    void addBadge(Badge badge, Integer id);

    boolean isEmpty();
}

