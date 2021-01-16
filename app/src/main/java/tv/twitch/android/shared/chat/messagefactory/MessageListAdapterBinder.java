package tv.twitch.android.shared.chat.messagefactory;


import tv.twitch.android.shared.chat.adapter.ChatAdapter;


public class MessageListAdapterBinder {
    private ChatAdapter adapter;
    private ChatMessageFactory messageFactory;

    /* ... */

    public void addRecentMessage(String line) { // TODO: __INJECT_METHOD
        this.adapter.addMessage(this.messageFactory.createRecentMessageItem(line), null);
    }
}
