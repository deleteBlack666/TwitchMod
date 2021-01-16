package tv.twitch.android.feature.theatre.metadata;


import android.content.Context;
import android.view.ViewGroup;

import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.hooks.General;


public final class ExtendedPlayerMetadataViewDelegate extends BaseViewDelegate {
    /* ... */

    public static final class Companion {
        /* ... */

        public final ExtendedPlayerMetadataViewDelegate create(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3) {
            // View inflate = LayoutInflater.from(context).inflate(R$layout.player_metadata_view_extended, viewGroup, false);

            int id = General.hookPlayerMetadataViewId(0); // TODO: __HOOK_ARG

            return null;
        }

        /* ... */
    }

    private void showSubscribeButton() {
        // this.followOrSubButtonContainer.addView(this.subButtonViewDelegate.getContentView()); // TODO: __REMOVE_CODE
    }
}
