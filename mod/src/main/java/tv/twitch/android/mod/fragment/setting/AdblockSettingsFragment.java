package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class AdblockSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "adblock_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_adblock_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_adblock_category");
    }
}
