package tv.twitch.android.shared.community.points.viewdelegate;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import tv.twitch.android.mod.bridge.interfaces.ICommunityPointsButtonViewDelegate;
import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.shared.chat.communitypoints.models.CommunityPointsModel;


public class CommunityPointsButtonViewDelegate implements ICommunityPointsButtonViewDelegate { // TODO: __IMPLEMENT
    private final ViewGroup buttonLayout = null;
    private final ImageView overlayIcon = null;

    /* ... */

    public CommunityPointsButtonViewDelegate(Context context, View view) {/* ... */}

    private final void showClaimAvailable(CommunityPointsModel communityPointsModel) {
        /* ... */

        HookController.setupPointClicker(this); // TODO: __INJECT_CODE
    }

    private void handleClaimError() {
        // Toast.makeText(getContext(), R$string.claim_error_message, 1).show(); // TODO: __REMOVE
        /* ... */
    }

    @Override
    public void maybeClickOnBonus() { // TODO: __INJECT_METHOD
        if (buttonLayout != null && buttonLayout.isEnabled()) {
            buttonLayout.performClick();
        }
    }

    /* ... */
}