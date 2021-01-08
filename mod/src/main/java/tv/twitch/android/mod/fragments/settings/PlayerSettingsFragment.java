package tv.twitch.android.mod.fragments.settings;


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
