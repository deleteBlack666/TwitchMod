package tv.twitch.android.sdk;


import tv.twitch.android.mod.hooks.Hook;
import tv.twitch.chat.ChatEmoticonSet;


public class ChatController {
    public ChatEmoticonSet[] mEmoticonSets = null;

    /* ... */

    public ChatEmoticonSet[] getEmoticonSets() { // TODO: __REPLACE_METHOD
        return Hook.hookChatEmoticonSet(this.mEmoticonSets);
    }

    public static /* synthetic */ ChatEmoticonSet[] access$000(ChatController chatController) { // TODO: __REPLACE_METHOD
        return Hook.hookChatEmoticonSet(chatController.mEmoticonSets);
    }

    /* ... */
}