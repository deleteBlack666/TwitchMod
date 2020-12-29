package tv.twitch.android.suggestion;


import tv.twitch.android.mod.hooks.Jump;
import tv.twitch.android.search.suggestion.SuggestableContent;


public class SearchSuggestionAdapterBinder {
    /* ... */

    public final void bind(SuggestableContent suggestableContent) {
        /* ... */

        if (suggestableContent instanceof SuggestableContent.SearchSuggestionPastQueries && !Jump.hideRecentSearch()) { // TODO :__JUMP_HOOK
            /* ... */
        }

        /* ... */
    }

    /* ... */
}