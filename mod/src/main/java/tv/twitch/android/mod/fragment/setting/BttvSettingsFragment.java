package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class BttvSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "bttv_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_bttv_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_chat_bttv");
    }
}
