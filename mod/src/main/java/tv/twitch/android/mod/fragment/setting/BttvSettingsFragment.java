package tv.twitch.android.mod.fragment.setting;


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
