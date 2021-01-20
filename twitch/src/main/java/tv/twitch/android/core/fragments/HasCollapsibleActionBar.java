package tv.twitch.android.core.fragments;


public interface HasCollapsibleActionBar {
    /* ... */

    void expandActionBar();

    CharSequence getToolbarTitle();

    void setToolbarTitle(String str);

    /* ... */
}
