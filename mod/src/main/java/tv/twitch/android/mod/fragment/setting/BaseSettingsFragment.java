package tv.twitch.android.mod.fragment.setting;


import android.app.Activity;
import android.os.Bundle;

import tv.twitch.android.app.core.ActivityUtil;
import tv.twitch.android.core.fragments.HasCollapsibleActionBar;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.libs.preference.Preference;
import tv.twitch.android.mod.libs.preference.PreferenceFragmentCompat;
import tv.twitch.android.mod.setting.SettingsController;


public abstract class BaseSettingsFragment extends PreferenceFragmentCompat implements IPreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(ResourcesManager.getXmlId(getXmlFilename()), rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        SettingsController.maybeShowRestartDialog(this.getActivity(), preference.getKey());
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getTitle());
    }

    protected void setTitle(String title) {
        if (title == null)
            title = "";

        Activity activity = getActivity();
        if (ActivityUtil.isActivityInvalid(activity)) {
            return;
        }

        if (!(activity instanceof HasCollapsibleActionBar)) {
            return;
        }

        HasCollapsibleActionBar hasCollapsibleActionBar = (HasCollapsibleActionBar) activity;

        hasCollapsibleActionBar.setToolbarTitle(title);
        hasCollapsibleActionBar.expandActionBar();
    }
}
