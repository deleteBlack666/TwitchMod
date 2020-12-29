package tv.twitch.android.feature.theatre.common;


import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.util.LimitedQueue;


public class FloatingChatPresenter {
    /* ... */

    public final LimitedQueue<Object> messageQueue = new LimitedQueue<>(General.getFloatingChatQueueSize()); // TODO: __HOOK_FIELD

    /* ... */
}
