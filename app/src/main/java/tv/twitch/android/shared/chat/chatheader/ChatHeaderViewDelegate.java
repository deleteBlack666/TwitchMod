package tv.twitch.android.shared.chat.chatheader;


import tv.twitch.android.core.mvp.viewdelegate.BaseViewDelegate;
import tv.twitch.android.mod.hooks.HookJump;


public class ChatHeaderViewDelegate extends BaseViewDelegate {
    @Override
    public void show() { // TODO: __INJECT_METHOD
        if (HookJump.shouldShowChatHeader())
            super.show();
    }
}
