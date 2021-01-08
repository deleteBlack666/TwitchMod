package tv.twitch.android.mod.fragments.settings;


public class PatchSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "patches_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_patches_preferences";
    }
}
