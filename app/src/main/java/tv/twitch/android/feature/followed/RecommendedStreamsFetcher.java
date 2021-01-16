package tv.twitch.android.feature.followed;


import tv.twitch.android.core.fetchers.NoParamDynamicContentFetcher;
import tv.twitch.android.mod.hooks.Hook;


public class RecommendedStreamsFetcher extends NoParamDynamicContentFetcher {
    /* ... */

    public final boolean hasMoreContent() { // TODO: __INJECT_METHOD
        return Hook.hookRecommendedStreamsFetcher(super.hasMoreContent());
    }

    /* ... */
}
