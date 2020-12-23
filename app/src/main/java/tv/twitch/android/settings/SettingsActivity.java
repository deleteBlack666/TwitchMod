package tv.twitch.android.settings;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import tv.twitch.android.mod.bridges.preference.Preference;
import tv.twitch.android.mod.bridges.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.bridges.interfaces.IPreferenceFragment;
import tv.twitch.android.util.FragmentUtil;


public final class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback { // TODO: __IMPLEMENT

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) { // TODO: __INJECT_METHOD
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
                getClassLoader(),
                pref.getFragment());

        String tag = caller.getTag();
        if (fragment instanceof IPreferenceFragment) {
            tag = ((IPreferenceFragment) fragment).getFragmentTag();
        }

        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);

        FragmentUtil.Companion.addOrRecreateFragment(this, fragment, tag, args);

        return true;
    }
}
