package tv.twitch.android.settings;


import androidx.appcompat.app.AppCompatActivity;
import tv.twitch.android.mod.libs.preference.Preference;
import tv.twitch.android.mod.libs.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.hooks.HookController;


public final class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback { // TODO: __IMPLEMENT

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) { // TODO: __INJECT_METHOD
        return HookController.onPreferenceStartFragment(this, caller, pref);
    }
}
