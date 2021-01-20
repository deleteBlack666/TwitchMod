package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class CreditSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "credit_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_credit_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_credits");
    }
}
