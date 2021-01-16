package tv.twitch.android.shared.chat.adapter;


import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import tv.twitch.android.shared.chat.adapter.item.ChatAdapterItem;
import tv.twitch.android.shared.chat.tracking.CensoredMessageTrackingInfo;


public interface ChatAdapter {
    /* ... */

    void addMessage(ChatAdapterItem chatAdapterItem, Function2<? super String, ? super CensoredMessageTrackingInfo, Unit> function2);

    /* ... */
}
