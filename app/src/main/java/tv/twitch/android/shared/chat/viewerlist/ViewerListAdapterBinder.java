package tv.twitch.android.shared.chat.viewerlist;


import android.text.TextUtils;

import java.util.List;

import tv.twitch.android.core.adapters.TwitchSectionAdapter;
import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.models.chat.Chatters;


public class ViewerListAdapterBinder {
    private TwitchSectionAdapter adapter;
    private Chatters orgChatters; // TODO: __INJECT_FIELD
    private String filterText; // TODO: __INJECT_FIELD

    /* ... */

    public final void setViewers(Chatters chatters) { // TODO: __REPLACE_METHOD
        addMoreViewers(chatters);
        orgChatters = chatters;
        filterChatters();
    }

    public final void clear() {/* ... */}

    private void filterChatters() { // TODO: __INJECT_METHOD
        if (orgChatters == null) {
            return;
        }

        clear();

        if (TextUtils.isEmpty(filterText)) {
            addItems("broadcaster", orgChatters.getBroadcasters());
            addItems("staff", orgChatters.getStaff());
            addItems("mods", orgChatters.getModerators());
            addItems("vips", orgChatters.getVips());
            addItems("viewers", orgChatters.getViewers());
        } else {
            final String lowerFilterText = filterText.toLowerCase();
            addItems("broadcaster", HookController.filterListOfUsers(orgChatters.getBroadcasters(), lowerFilterText));
            addItems("staff", HookController.filterListOfUsers(orgChatters.getStaff(), lowerFilterText));
            addItems("mods", HookController.filterListOfUsers(orgChatters.getModerators(), lowerFilterText));
            addItems("vips", HookController.filterListOfUsers(orgChatters.getVips(), lowerFilterText));
            addItems("viewers", HookController.filterListOfUsers(orgChatters.getViewers(), lowerFilterText));
        }
    }

    public final void setFilterText(String text) { // TODO: __INJECT_METHOD
        filterText = text;
        filterChatters();
    }
    
    private final void addMoreViewers(Chatters chatters) {/* ... */}

    private final void addItems(String str, List<String> list) {/* ... */}
}
