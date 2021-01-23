package tv.twitch.android.mod.setting;


import android.app.Activity;
import android.text.TextUtils;


import tv.twitch.android.app.core.ActivityUtil;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.models.Preference;
import tv.twitch.android.mod.preference.PreferenceManager;
import tv.twitch.android.mod.util.Helper;
import tv.twitch.android.mod.util.Logger;


public class SettingsController {
    private static final Preference[] RESTART_LIST = {Preference.PLAYER_ADBLOCK, Preference.FFZ_BADGES,
    Preference.DISABLE_GOOGLE_BILLING, Preference.HIDE_DISCOVER_TAB, Preference.HIDE_ESPORTS_TAB,
    Preference.BTTV_EMOTES};

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

        Preference pref = Preference.lookupByKey(key);
        if (pref == null)
            return false;

        for (Preference p : RESTART_LIST) {
            if (p == pref) {
                return true;
            }
        }

        return false;
    }

    public static class OnBuildClickListener implements tv.twitch.android.mod.libs.preference.Preference.OnPreferenceClickListener {
        private static final int CLICKS = 15;

        private int clicked = 0;

        private void disableDevMod() {
            PreferenceManager.INSTANCE.writeBoolean(Preference.DEV_MODE.getKey(), false);
            Helper.showToast(ResourcesManager.getString("mod_dev_mode_disabled"));
        }

        private void enableDevMod() {
            PreferenceManager.INSTANCE.writeBoolean(Preference.DEV_MODE.getKey(), true);
            Helper.showToast(ResourcesManager.getString("mod_dev_mode_enabled"));
        }

        @Override
        public boolean onPreferenceClick(tv.twitch.android.mod.libs.preference.Preference preference) {
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
