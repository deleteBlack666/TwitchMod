package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class PlayerSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "player_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_player_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_player_category");
    }
}
