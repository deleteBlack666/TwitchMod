package tv.twitch.android.feature.followed;


import tv.twitch.android.core.fetchers.NoParamDynamicContentFetcher;
import tv.twitch.android.mod.hooks.General;


public class FollowedGamesFetcher extends NoParamDynamicContentFetcher {
    /* ... */

    public final boolean hasMoreContent() { // TODO: __INJECT_METHOD
        return General.hookFollowedGamesFetcher(super.hasMoreContent());
    }

    /* ... */
}
