package tv.twitch.android.mod.fragments.settings;


public class BttvSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "bttv_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_bttv_preferences";
    }
}
