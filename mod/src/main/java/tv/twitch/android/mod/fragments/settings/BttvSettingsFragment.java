package tv.twitch.android.mod.fragments.settings;


import android.os.Bundle;

import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.bridge.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.settings.SettingsController;


public class BttvSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    private static final String TAG = "bttv_preferences";
    private static final String XML_FILENAME = "mod_bttv_preferences";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(XML_FILENAME), rootKey);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {

        Preferences pf = Preferences.lookupKey(preference.getKey());
        if (pf != null) {
            if (pf == Preferences.FFZ_BADGES) {
                SettingsController.showRestartDialog(getActivity());
            }
        }
        return super.onPreferenceTreeClick(preference);
    }
}
