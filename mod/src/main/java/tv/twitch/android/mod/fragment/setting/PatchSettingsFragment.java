package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class PatchSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "patches_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_patches_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_patch");
    }
}
