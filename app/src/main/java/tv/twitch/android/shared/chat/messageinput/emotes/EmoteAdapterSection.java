package tv.twitch.android.shared.chat.messageinput.emotes;


import tv.twitch.android.mod.hooks.General;

public class EmoteAdapterSection {
    private String emoteSetId;

    /* ... */

    private String constructHeaderFromEmotes() {
        String res = null;

        /* ... */

        return General.hookSetName(res, this.emoteSetId); // TODO: __INJECT_CODE
    }

    /* ... */
}