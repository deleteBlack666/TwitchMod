package tv.twitch.android.mod.settings;


import android.app.Activity;
import android.text.TextUtils;


import tv.twitch.android.app.core.ActivityUtil;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.util.Helper;
import tv.twitch.android.mod.util.Logger;


public class SettingsController {
    private static Preferences[] RESTART_LIST = {Preferences.PLAYER_ADBLOCK, Preferences.FFZ_BADGES,
    Preferences.DISABLE_GOOGLE_BILLING, Preferences.HIDE_DISCOVER_TAB, Preferences.HIDE_ESPORTS_TAB,
    Preferences.BTTV_EMOTES};

    public static void maybeShowRestartDialog(Activity activity, String key) {
        if (TextUtils.isEmpty(key)) {
            Logger.warning("empty key");
            return;
        }

        if (ActivityUtil.isActivityInvalid(activity)) {
            Logger.error("invalid activity");
            return;
        }

        if (isRestartKey(key)) {
            Helper.showRestartDialog(activity, ResourcesManager.getString("restart_dialog_text"));
        }
    }

    private static boolean isRestartKey(String key) {
        if (TextUtils.isEmpty(key)) {
            Logger.warning("empty key");
            return false;
        }

        Preferences pref = Preferences.lookupKey(key);
        if (pref == null)
            return false;

        for (Preferences p : RESTART_LIST) {
            if (p == pref) {
                return true;
            }
        }

        return false;
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
