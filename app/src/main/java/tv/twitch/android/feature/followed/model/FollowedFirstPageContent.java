package tv.twitch.android.feature.followed.model;


import java.util.Collections;
import java.util.List;

import tv.twitch.android.mod.hooks.Jump;
import tv.twitch.android.models.GameModel;


public class FollowedFirstPageContent {
    /* ... */

    public final List<GameModel> getGames() {
        if (Jump.shouldShowFollowedGames()) // TODO: __INJECT_CODE
            return Collections.emptyList();

        /* ... */

        return null;
    }

    /* ... */
}
