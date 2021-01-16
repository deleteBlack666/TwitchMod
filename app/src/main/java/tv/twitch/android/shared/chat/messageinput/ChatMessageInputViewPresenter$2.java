package tv.twitch.android.shared.chat.messageinput;


import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.shared.chat.events.ChannelSetEvent;


public class ChatMessageInputViewPresenter$2 {
    /* ... */

    public final void accept(ChannelSetEvent channelSetEvent) {
        HookController.setCurrentChannel(channelSetEvent); // TODO: __INJECT_CODE

        /* ... */
    }

    /* ... */
}
