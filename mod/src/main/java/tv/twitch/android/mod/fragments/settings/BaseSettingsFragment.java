package tv.twitch.android.mod.fragments.settings;


import android.os.Bundle;

import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.bridge.preference.PreferenceFragmentCompat;
import tv.twitch.android.mod.settings.SettingsController;


public abstract class BaseSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(getXmlFilename()), rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        SettingsController.maybeShowRestartDialog(this.getActivity(), preference.getKey());
        return super.onPreferenceTreeClick(preference);
    }
}
