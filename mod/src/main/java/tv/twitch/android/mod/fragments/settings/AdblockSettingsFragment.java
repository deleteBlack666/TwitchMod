package tv.twitch.android.mod.fragments.settings;


import android.os.Bundle;

import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.bridge.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.settings.SettingsController;


public class AdblockSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    private static final String TAG = "adblock_preferences";
    private static final String XML_FILENAME = "mod_adblock_preferences";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(XML_FILENAME), rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {

        Preferences pf = Preferences.lookupKey(preference.getKey());
        if (pf != null) {
            switch (pf) {
                case PLAYER_ADBLOCK:
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
