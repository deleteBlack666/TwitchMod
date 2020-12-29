package tv.twitch.android.feature.theatre.chomments;


import tv.twitch.android.mod.hooks.General;
import tv.twitch.android.models.chomments.ChommentModel;
import tv.twitch.android.shared.chat.chomments.ChommentRecyclerItem;


public class ChommentsAdapterBinder {
    /* ... */

    private ChommentRecyclerItem createListItemForChomment(ChommentModel chommentModel) {
        chommentModel = General.maybeFilterThisChomment(chommentModel); // TODO: __INJECT_CODE
        if (chommentModel == null) { // TODO: __INJECT_CODE
            return null;
        }

        /* ... */

        return null;
    }

    private void addItemToAdapter(ChommentRecyclerItem chommentRecyclerItem) {
        if (chommentRecyclerItem == null) { // TODO: __INJECT_CODE
            return;
        }

        /* ... */
    }

    /* ... */
}
