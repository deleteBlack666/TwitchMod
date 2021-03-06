package tv.twitch.android.app.core;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridges.LoaderLS;
import tv.twitch.android.mod.fragments.SleepTimerFragment;
import tv.twitch.android.mod.utils.Helper;


public class MainActivity extends AppCompatActivity implements SleepTimerFragment.SleepTimerListener { // TODO: __IMPLEMENT
    TwitchAccountManager mAccountManager;

    /* ... */

    private FragmentManager.OnBackStackChangedListener mBackStackListener = new FragmentManager.OnBackStackChangedListener() { // TODO: __REPLACE_FIELD
        @Override
        public void onBackStackChanged() {
            Helper.maybeShowModInfoBanner(MainActivity.this, mAccountManager);
        }
    };

    @Override
    public void onTimeChanged(int hour, int minute) { // TODO: __INJECT_METHOD
        LoaderLS.getInstance().onTimeChanged(hour, minute);
    }


    /* ... */
}
