package tv.twitch.android.mod.settings;


import android.app.Activity;


import tv.twitch.android.mod.bridges.ResourcesManager;
import tv.twitch.android.mod.bridges.preference.Preference;
import tv.twitch.android.mod.utils.Helper;


public class SettingsController {
    public static void showRestartDialog(Activity activity) {
        Helper.showRestartDialog(activity, ResourcesManager.getString("restart_dialog_text"));
    }

    public static class OnBuildClickListener implements Preference.OnPreferenceClickListener {
        private static final int CLICKS = 15;

        private int clicked = 0;

        private void disableDevMod() {
            PreferenceManager.INSTANCE.updateBoolean("mod_dev_mode", false);
            Helper.showToast(ResourcesManager.getString("mod_dev_mode_disabled"));
        }

        private void enableDevMod() {
            PreferenceManager.INSTANCE.updateBoolean("mod_dev_mode", true);
            Helper.showToast(ResourcesManager.getString("mod_dev_mode_enabled"));
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            clicked++;
            handleClickEvent();

            return true;
        }

        private void handleClickEvent() {
            if (clicked == CLICKS) {
                if (PreferenceManager.INSTANCE.isDevModeOn()) {
                    disableDevMod();
                } else {
                    enableDevMod();
                }
                clicked = 0;
            } else if (clicked % 5 == 0 && clicked > 0) {
                if (PreferenceManager.INSTANCE.isDevModeOn()) {
                    disableDevMod();
                    clicked = 0;
                } else {
                    if (clicked == 10) {
                        Helper.showToast("monkaHmm");
                    } else {
                        Helper.showToast("Jebaited");
                    }
                }
            }
        }
    }
}
