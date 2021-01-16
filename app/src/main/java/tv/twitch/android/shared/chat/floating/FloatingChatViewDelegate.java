package tv.twitch.android.shared.chat.floating;


import tv.twitch.android.mod.hooks.General;


public class FloatingChatViewDelegate {
    /* ... */

    public FloatingChatViewDelegate() {
        /* ... */

        // this.messageCarouselViewDelegate = new CompactChatViewDelegate((LinearLayout) findView(R$id.messages_container), Hooks.getFloatingChatQueueSize());
        int count = General.getFloatingChatQueueSize(); // TODO: __HOOK_ARG

        /* ... */
    }

    /* ... */
}
