package tv.twitch.android.feature.theatre.chomments;


import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.hooks.Jump;


public class ChommentsHeaderViewDelegate extends BaseViewDelegate {
    @Override
    public void show() { // TODO: __INJECT_METHOD
        if (Jump.shouldShowChatHeader())
            super.show();
    }
}
