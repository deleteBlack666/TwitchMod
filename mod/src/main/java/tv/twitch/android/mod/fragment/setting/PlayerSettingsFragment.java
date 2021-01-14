package tv.twitch.android.mod.fragment.setting;


public class PlayerSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "player_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_player_preferences";
    }
}
