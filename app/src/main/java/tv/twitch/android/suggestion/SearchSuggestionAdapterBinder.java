package tv.twitch.android.suggestion;


import tv.twitch.android.mod.hooks.HookJump;
import tv.twitch.android.search.suggestion.SuggestableContent;


public class SearchSuggestionAdapterBinder {
    /* ... */

    public final void bind(SuggestableContent suggestableContent) {
        /* ... */

        if (suggestableContent instanceof SuggestableContent.SearchSuggestionPastQueries && !HookJump.hideRecentSearch()) { // TODO :__JUMP_HOOK
            /* ... */
        }

        /* ... */
    }

    /* ... */
}