package tv.twitch.android.mod.bridge;


import android.content.Context;
import android.os.Process;

import androidx.annotation.NonNull;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import tv.twitch.android.app.consumer.TwitchApplication;
import tv.twitch.android.mod.badge.BadgeManager;
import tv.twitch.android.mod.emote.EmoteManager;
import tv.twitch.android.mod.fragment.SleepTimerFragment;
import tv.twitch.android.mod.models.BuildInfo;
import tv.twitch.android.mod.preference.PreferenceManager;
import tv.twitch.android.mod.util.ChatMesssageFilteringUtil;
import tv.twitch.android.mod.util.Helper;


public class LoaderLS extends TwitchApplication implements SleepTimerFragment.SleepTimerListener, SleepTimer.Callback {
    private static final String APK_BUILD_INFO_TEMPLATE = "BUILD ";

    private static final String AUTHORIZATION = Helper.decodeBase64("T0F1dGggMm9uMHFjaHExcmU4Mml5Y3ZieG02MWxhaGVpYTZh");

    private BuildInfo buildInfo;

    private static LoaderLS sInstance = null;

    private ISleepTimer mTimer;

    public static class BuildInfoImpl implements BuildInfo {
        private final String buildInfo;
        private final int buildNumber;
        private final String buildVersion;

        public BuildInfoImpl(String bi, int bn, String bv) {
            buildInfo = bi;
            buildNumber = bn;
            buildVersion = bv;
        }

        @Override
        public String getBuildInfo() {
            return buildInfo;
        }

        @Override
        public int getBuildNumber() {
            return buildNumber;
        }

        @Override
        public String getBuildVersion() {
            return buildVersion;
        }
    }

    public String getChangelog() {
        return Helper.readTextFromAssets(this, "changelog.txt");
    }

    public String getBuildInfo() {
        return buildInfo.getBuildInfo() + " b" + buildInfo.getBuildNumber();
    }

    public int getBuildNumber() {
        return buildInfo.getBuildNumber();
    }

    public String getBuildVersion() {
        return buildInfo.getBuildVersion();
    }

    @NonNull
    public static LoaderLS getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("LoaderLS instance is null");
        }

        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        initLoader();
        super.onCreate();
        fetchBttv();
        fetchBadges();
        setFilterBlocklist();
    }

    private void fetchBadges() {
        if (PreferenceManager.INSTANCE.isFfzBadgesEnabled()) {
            BadgeManager.INSTANCE.fetchBadges();
        }
    }

    private void setFilterBlocklist() {
        ChatMesssageFilteringUtil.INSTANCE.updateBlocklist(PreferenceManager.INSTANCE.getUserFilterText());
    }

    private void fetchBttv() {
        if (PreferenceManager.INSTANCE.showBttvEmotesInChat())
            EmoteManager.INSTANCE.fetchGlobalEmotes();
    }

    private HashMap<String, String> getConfigMap() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("build.properties");

            HashMap<String, String> configMap = Helper.parseConfig(inputStream);

            try {
                inputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return configMap;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new HashMap<>();
    }

    private void setBuildInfo() {
        HashMap<String, String> configMap = getConfigMap();

        buildInfo = new BuildInfoImpl(configMap.get("build_info"),
                Integer.parseInt(configMap.get("build_num")),
                configMap.get("build_ver"));
    }

    private void initLoader() {
        sInstance = this;
        setBuildInfo();
        PreferenceManager.INSTANCE.initialize(this);
        mTimer = new SleepTimer(this);
    }

    public static void killApp() {
        Process.killProcess(Process.myPid());
    }

    @Override
    public void onTimeChanged(int hour, int minute) {
        if (mTimer != null) {
            mTimer.cancel();
        }

        if (hour == 0 && minute == 0) {
            Helper.showToast(ResourcesManager.getString("mod_sleep_timer_canceled"));
            return;
        }

        final int seconds = hour * 3600 + minute * 60;

        mTimer.start(seconds);
        showTimerToast(seconds * 1000);
    }

    private void showTimerToast(long l) {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(l);
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(l % 3_600_000);
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(l % 60_000);

        if (hours == 0) {
            if (minutes == 0) {
                final String qSeconds = ResourcesManager.getQuantityString("timer_seconds", seconds, seconds);
                Helper.showToast(ResourcesManager.getString("mod_sleep_timer_stop_after", qSeconds));
            } else {
                final String qMinutes = ResourcesManager.getQuantityString("timer_minutes", minutes, minutes);
                Helper.showToast(ResourcesManager.getString("mod_sleep_timer_stop_after", qMinutes));
            }
        } else {
            final String qHours = ResourcesManager.getQuantityString("timer_hours", hours, hours);
            Helper.showToast(ResourcesManager.getString("mod_sleep_timer_stop_after", qHours));
        }
    }

    public static String getAuthorization() {
        return AUTHORIZATION;
    }

    @Override
    public void onDone() {
        killApp();
    }

    @Override
    public void onInfoTick(long second) {
        showTimerToast(second);
    }
}
