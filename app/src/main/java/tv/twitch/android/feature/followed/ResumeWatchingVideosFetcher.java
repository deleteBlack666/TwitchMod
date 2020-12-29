package tv.twitch.android.feature.followed;


import tv.twitch.android.core.fetchers.NoParamDynamicContentFetcher;
import tv.twitch.android.mod.hooks.General;


public class ResumeWatchingVideosFetcher extends NoParamDynamicContentFetcher {
    /* ... */

    public final boolean hasMoreContent() { // TODO: __INJECT_METHOD
        return General.hookResumeWatchingFetcher(super.hasMoreContent());
    }

    /* ... */
}
