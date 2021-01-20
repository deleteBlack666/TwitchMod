package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class ChatSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "chat_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_chat_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_chat_category");
    }
}
