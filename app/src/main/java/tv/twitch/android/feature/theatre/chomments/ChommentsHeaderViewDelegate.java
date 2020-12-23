package tv.twitch.android.feature.theatre.chomments;


import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.bridges.Hooks;


public class ChommentsHeaderViewDelegate extends BaseViewDelegate {
    @Override
    public void show() { // TODO: __INJECT_METHOD
        if (Hooks.shouldShowChatHeader())
            super.show();
    }
}
