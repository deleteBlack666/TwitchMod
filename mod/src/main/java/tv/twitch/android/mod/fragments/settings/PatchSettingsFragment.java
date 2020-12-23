package tv.twitch.android.mod.fragments.settings;


import android.os.Bundle;

import tv.twitch.android.mod.bridges.preference.Preference;
import tv.twitch.android.mod.bridges.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.bridges.ResourcesManager;
import tv.twitch.android.mod.bridges.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.settings.SettingsController;


public class PatchSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    private static final String TAG = "patches_preferences";
    private static final String XML_FILENAME = "mod_patches_preferences";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(XML_FILENAME), rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {

        Preferences pf = Preferences.lookupKey(preference.getKey());
        if (pf != null) {
            if (pf == Preferences.DISABLE_GOOGLE_BILLING) {
                SettingsController.showRestartDialog(getActivity());
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
