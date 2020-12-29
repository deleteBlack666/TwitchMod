package tv.twitch.android.feature.theatre;


import tv.twitch.android.api.parsers.PlayableModelParser;
import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.models.Playable;
import tv.twitch.android.shared.analytics.PageViewTracker;


public class ModelTheatreModeTracker {
    /* ... */

    public ModelTheatreModeTracker(PlayableModelParser playableParser, Playable playable, PageViewTracker pageViewTracker2) {
        General.requestEmotes(playableParser, playable); // TODO: __INJECT_CALL

        /* ... */
    }

    /* ... */
}
