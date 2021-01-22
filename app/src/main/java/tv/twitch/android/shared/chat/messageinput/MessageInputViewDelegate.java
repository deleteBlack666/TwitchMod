package tv.twitch.android.shared.chat.messageinput;


import tv.twitch.android.mod.hooks.Hook;

public class MessageInputViewDelegate {
    /* ... */

    public final void setBitsPickerButtonVisible(boolean org) {
        org = Hook.hookBitsPickerButtonVisible(org);  // __HOOK_PARAM
        int o = 1;
        /* ... */
    }

    /* ... */
}
