package tv.twitch.android.mod.fragments.settings;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.bridge.preference.PreferenceFragmentCompat;

import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.settings.SettingsController;


public class MainSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    private static final String TAG = "main_preferences";
    private static final String XML_FILENAME = "mod_main_preferences";

    private static final String BUILD_INFO_KEY = "BUILD_INFO";

    private final SettingsController.OnBuildClickListener buildClickListener = new SettingsController.OnBuildClickListener();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(XML_FILENAME), rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Preference preference = findPreference(BUILD_INFO_KEY);
        if (preference != null) {
            preference.setTitle("TwitchMod v" + LoaderLS.getVersionName());
            preference.setSummary(LoaderLS.getBuildInfo());
            preference.setOnPreferenceClickListener(buildClickListener);
        }
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
