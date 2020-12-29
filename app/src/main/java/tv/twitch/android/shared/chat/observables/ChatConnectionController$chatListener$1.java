package tv.twitch.android.shared.chat.observables;


import tv.twitch.android.mod.hooks.General;
import tv.twitch.chat.ChatLiveMessage;


public class ChatConnectionController$chatListener$1 {
    final ChatConnectionController this$0 = null;

    /* ... */

    public void onChannelMessageReceived(int i, ChatLiveMessage[] chatLiveMessageArr) {
        chatLiveMessageArr = General.hookReceivedMessages(this$0, chatLiveMessageArr);  // TODO: __HOOK_PARAM

        /* ... */
    }

    /* ... */
}
