package tv.twitch.android.shared.chat.messagefactory;


import tv.twitch.android.shared.chat.adapter.ChatAdapter;


public class MessageListAdapterBinder {
    private ChatAdapter adapter;
    private ChatMessageFactory messageFactory;

    /* ... */

    public void addRecentMessage(String line) { // TODO: __INJECT_METHOD
        ChatAdapter.DefaultImpls.addMessage$default(this.adapter, this.messageFactory.createRecentMessageItem(line), null, 2, null);
    }
}
