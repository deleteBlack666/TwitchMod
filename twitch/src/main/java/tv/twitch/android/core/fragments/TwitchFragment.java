package tv.twitch.android.core.fragments;


import androidx.fragment.app.Fragment;

public class TwitchFragment extends Fragment {
    private HasCollapsibleActionBar getHasCollapsibleActionBar() {
        if (getActivity() instanceof HasCollapsibleActionBar) {
            return (HasCollapsibleActionBar) getActivity();
        }
        return null;
    }
}
