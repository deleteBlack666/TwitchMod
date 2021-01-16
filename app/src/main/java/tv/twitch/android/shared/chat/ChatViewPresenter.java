package tv.twitch.android.shared.chat;


import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.mod.hooks.HookJump;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.models.streams.StreamType;
import tv.twitch.android.shared.chat.events.ChatConnectionEvents;
import tv.twitch.android.shared.chat.observables.ChatConnectionController;


public class ChatViewPresenter {
    public final LiveChatSource liveChatSource = null;

    private ChannelInfo channel;
    private String playbackSessionID;
    private StreamType streamType;

    /* ... */

    private final ChatConnectionController chatConnectionController = null;

    /* ... */

    public final void onUserBanStateUpdated(boolean z) {
        if (z && HookJump.isBypassChatBanEnabled()) { // TODO: __INJECT_CODE
            z = false;
            anonConnect();
        }

        /* ... */
    }

    @SuppressWarnings("ConstantConditions")
    private void anonConnect() { // TODO: __INJECT_METHOD
        ChannelInfo backupChannelInfo = this.channel;

        if (chatConnectionController != null)
            chatConnectionController.setViewerId(0);
        this.channel = null;
        this.setChannel(backupChannelInfo, playbackSessionID, streamType);
        this.channel = backupChannelInfo;
    }

    public final void setChannel(ChannelInfo channelInfo2, String str, StreamType streamType2) {/* ... */}

    public final void onChannelStateChanged(ChatConnectionEvents chatConnectionEvent) {
        HookController.injectRecentMessages(chatConnectionEvent, liveChatSource, channel);

        /* ... */
    }

    /* ... */
}