package tv.twitch.android.mod.fragment.setting;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.libs.preference.Preference;

import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.setting.SettingsController;


public class MainSettingsFragment extends BaseSettingsFragment {
    private static final String BUILD_INFO_KEY = "BUILD_INFO";

    private final SettingsController.OnBuildClickListener buildClickListener = new SettingsController.OnBuildClickListener();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Preference preference = findPreference(BUILD_INFO_KEY);
        if (preference != null) {
            preference.setTitle("TwitchMod v" + LoaderLS.getInstance().getBuildVersion());
            preference.setSummary(LoaderLS.getInstance().getBuildInfo());
            preference.setOnPreferenceClickListener(buildClickListener);
        }
    }

    @Override
    public String getFragmentTag() {
        return "main_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_main_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("system");
    }
}
