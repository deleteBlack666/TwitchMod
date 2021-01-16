package tv.twitch.android.feature.theatre.common;


import tv.twitch.android.mod.hooks.Hook;
import tv.twitch.android.util.LimitedQueue;


public class FloatingChatPresenter {
    /* ... */

    public final LimitedQueue<Object> messageQueue = new LimitedQueue<>(Hook.getFloatingChatQueueSize()); // TODO: __HOOK_FIELD

    /* ... */
}
