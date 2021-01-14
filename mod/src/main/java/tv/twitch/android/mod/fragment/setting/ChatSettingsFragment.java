package tv.twitch.android.mod.fragment.setting;


public class ChatSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "chat_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_chat_preferences";
    }
}
