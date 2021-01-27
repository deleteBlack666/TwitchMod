package tv.twitch.android.shared.chat.viewerlist;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;

import tv.twitch.android.core.mvp.viewdelegate.RxViewDelegate;
import tv.twitch.android.core.mvp.viewdelegate.ViewDelegateEvent;
import tv.twitch.android.mod.hooks.HookController;
import tv.twitch.android.mod.util.Logger;
import tv.twitch.android.shared.ui.elements.list.ContentListViewDelegate;
import tv.twitch.android.shared.ui.elements.list.ListViewState;


public class ViewerListViewDelegate extends RxViewDelegate<ListViewState, ViewerListViewDelegate.Event> {
    /* ... */
    private ImageView dismissButton;

    private SearchView searchView; // TODO: __INJECT_FIELD
    /* ... */

    public ViewerListViewDelegate(Context context, View view, ContentListViewDelegate contentListViewDelegate2) {
        /* ... */

        searchView = HookController.setupSearchView(view); // TODO: __INJECT_CODE

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        /* ... */

        observeSearchView();
    }

    private void renderSearchViewVisibility(ListViewState listViewState) { // TODO: __INJECT_METHOD
        if (listViewState == null) {
            Logger.error("listViewState is null");
            return;
        }

        if (searchView == null) {
            Logger.error("searchView is null");
            return;
        }

        if (listViewState.equals(ListViewState.Empty.INSTANCE)) {
            searchView.setVisibility(View.GONE);
        } else if (listViewState.equals(ListViewState.Loading.INSTANCE)) {
            searchView.setVisibility(View.GONE);
        } else if (listViewState.equals(ListViewState.Error.INSTANCE)) {
            searchView.setVisibility(View.GONE);
        } else if (listViewState.equals(ListViewState.Loaded.INSTANCE)) {
            searchView.setVisibility(View.VISIBLE);
        }
    }

    private void observeSearchView() { // TODO: __INJECT_METHOD
        if (searchView == null) {
            Logger.error("searchView is null");
            return;
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pushEvent(new Event.Filter(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pushEvent(new Event.Filter(newText));
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                pushEvent(new Event.Filter(null));
                return false;
            }
        });
    }

    public static abstract class Event implements ViewDelegateEvent {

        public static class Filter extends Event { // TODO: __INJECT_CLASS
            public final String text;

            public Filter(String filterText) { // TODO: __HOOK_SUPER_CALL
                text = filterText;
            }

            public String getText() {
                return text;
            }
        }

        private Event() {}

        public Event(Object defaultConstructorMarker) {
            this();
        }

        /* ... */
    }

    public void render(ListViewState listViewState) {
        /* ... */

        renderSearchViewVisibility(listViewState); // TODO: __INJECT_CODE
    }

}
