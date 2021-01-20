package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class ViewSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "view_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_view_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_view");
    }
}
