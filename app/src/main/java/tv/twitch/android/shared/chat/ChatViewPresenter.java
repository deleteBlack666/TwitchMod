package tv.twitch.android.shared.chat;


import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.mod.hooks.Jump;
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
        if (z && Jump.isBypassChatBanEnabled()) { // TODO: __INJECT_CODE
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
        General.injectRecentMessages(chatConnectionEvent, liveChatSource, channel);

        /* ... */
    }

    /* ... */
}