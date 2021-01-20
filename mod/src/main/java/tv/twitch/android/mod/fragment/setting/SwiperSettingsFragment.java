package tv.twitch.android.mod.fragment.setting;


import tv.twitch.android.mod.bridge.ResourcesManager;

public class SwiperSettingsFragment extends BaseSettingsFragment {
    @Override
    public String getFragmentTag() {
        return "swiper_preferences";
    }

    @Override
    public String getXmlFilename() {
        return "mod_swiper_preferences";
    }

    @Override
    public String getTitle() {
        return ResourcesManager.getString("mod_category_settings_swipe");
    }
}
