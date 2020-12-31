package tv.twitch.android.mod.hooks;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import tv.twitch.android.app.core.ViewExtensionsKt;
import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.bridge.interfaces.IBottomPlayerControlOverlayViewDelegate;
import tv.twitch.android.mod.bridge.interfaces.ICommunityPointsButtonViewDelegate;
import tv.twitch.android.mod.bridge.interfaces.IEmotePickerViewDelegate;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.bridge.interfaces.ISharedPanelWidget;
import tv.twitch.android.mod.bridge.preference.Preference;
import tv.twitch.android.mod.bridge.preference.PreferenceFragmentCompat;
import tv.twitch.android.mod.fragments.ModInfoBannerFragment;
import tv.twitch.android.mod.fragments.SleepTimerFragment;
import tv.twitch.android.mod.fragments.settings.MainSettingsFragment;
import tv.twitch.android.mod.settings.PreferenceManager;
import tv.twitch.android.mod.util.ClipDownloader;
import tv.twitch.android.mod.util.FragmentUtil;
import tv.twitch.android.mod.util.Logger;
import tv.twitch.android.models.clips.ClipModel;

public final class Controller {
    public static View setupLockButton(View container, IBottomPlayerControlOverlayViewDelegate delegate) {
        if (container == null) {
            Logger.error("container is null");
            return null;
        }

        int buttonId = ResourcesManager.getId("lock_button");
        if (buttonId == 0) {
            Logger.error("buttonId == 0");
            return null;
        }

        View buttonView = container.findViewById(buttonId);
        if (buttonView == null) {
            Logger.error("buttonView is null");
            return null;
        }

        setupLockButtonClickListener(buttonView, delegate);

        return buttonView;
    }

    public static void updateLockButtonState(ImageView lockButton) {
        if (lockButton == null)
            return;

        int lockDrawableId = ResourcesManager.getDrawableId("ic_lock");
        if (lockDrawableId == 0) {
            Logger.error("ic_lock not found");
            return;
        }

        int unlockDrawableId = ResourcesManager.getDrawableId("ic_unlock");
        if (unlockDrawableId == 0) {
            Logger.error("ic_unlock not found");
            return;
        }

        if (Jump.shouldLockSwiper()) {
            lockButton.setImageResource(unlockDrawableId);
        } else {
            lockButton.setImageResource(lockDrawableId);
        }
    }

    public static void setupLockButtonClickListener(View lockButton, final IBottomPlayerControlOverlayViewDelegate delegate) {
        if (lockButton == null) {
            Logger.error("lockButton is null");
            return;
        }

        if (delegate == null) {
            Logger.error("delegate is null");
            return;
        }

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                General.setLockSwiper(!Jump.shouldLockSwiper());
                delegate.updateLockButtonState();
            }
        });
    }

    public static void changeLockButtonVisibility(ImageView lockButton, boolean z) {
        if (lockButton == null) {
            Logger.error("lockButton is null");
            return;
        }

        if (!Jump.isSwipperEnabled() || !Jump.shouldShowLockButton()) {
            lockButton.setVisibility(View.GONE);
            return;
        }

        lockButton.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public static View setupRefreshButton(View container, final IBottomPlayerControlOverlayViewDelegate delegate) {
        if (container == null) {
            Logger.error("container is null");
            return null;
        }
        int buttonId = ResourcesManager.getId("refresh_button");
        if (buttonId == 0) {
            Logger.error("buttonId == 0");
            return null;
        }

        ImageView refreshButton = container.findViewById(buttonId);
        if (refreshButton == null) {
            Logger.error("refreshButton is null");
            return null;
        }

        setupRefreshButtonClickListener(refreshButton, delegate);

        return refreshButton;
    }

    public static void setupRefreshButtonClickListener(View refreshButton, final IBottomPlayerControlOverlayViewDelegate delegate) {
        if (refreshButton == null) {
            Logger.error("refreshButton is null");
            return;
        }

        if (delegate == null) {
            Logger.error("delegate is null");
            return;
        }

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.clickRefresh();
            }
        });
    }

    public static View setupUptime(View container, final IBottomPlayerControlOverlayViewDelegate delegate) {
        if (container == null) {
            Logger.error("container is null");
            return null;
        }

        int uptimeViewId = ResourcesManager.getId("stream_uptime");
        if (uptimeViewId == 0) {
            Logger.error("uptimeViewId == 0");
            return null;
        }

        return container.findViewById(uptimeViewId);
    }

    public static View setupUptimeIcon(View container, final IBottomPlayerControlOverlayViewDelegate delegate) { // TODO: __INJECT_METHOD
        if (container == null) {
            Logger.error("container is null");
            return null;
        }

        int uptimeIconId = ResourcesManager.getId("stream_uptime_icon");
        if (uptimeIconId == 0) {
            Logger.error("uptimeIconId == 0");
            return null;
        }

        return container.findViewById(uptimeIconId);
    }


    public static FrameLayout setupDownloadButton(View container, ClipModel clipModel,
                                                  ISharedPanelWidget sharedPanelWidget) {
        int buttonId = ResourcesManager.getId("download_model");
        if (buttonId == 0) {
            Logger.error("buttonId == 0");
            return null;
        }

        FrameLayout downloadButton = container.findViewById(buttonId);
        if (downloadButton != null) {
            ViewExtensionsKt.visibilityForBoolean(downloadButton, clipModel != null);
            setupClipDownloader(downloadButton, sharedPanelWidget);
        }

        return downloadButton;
    }

    public static void setupClipDownloader(FrameLayout downloadButton, ISharedPanelWidget panelWidget) {
        if (downloadButton == null) {
            Logger.error("downloadButton is null");
            return;
        }

        downloadButton.setOnClickListener(new ClipDownloader(panelWidget));
    }

    public static void setupTimerSleepButton(final Context context, ImageView button) {
        if (button == null) {
            Logger.error("button is null");
            return;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.showDialogFragment(context, new SleepTimerFragment(), "sleepTimePicker");
            }
        });
    }

    public static View getTimerSleepButton(View container) {
        return container.findViewById(ResourcesManager.getId("sleep_timer_button"));
    }

    public static void setupBttvEmotesButtonClickListener(ImageView bttvEmotesButton,
                                                          final IEmotePickerViewDelegate delegate) {
        if (bttvEmotesButton == null) {
            Logger.error("bttvEmotesButton is null");
            return;
        }

        if (delegate == null) {
            Logger.error("delegate is null");
            return;
        }

        bttvEmotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.scrollToBttvSection();
            }
        });

        bttvEmotesButton.setVisibility(Jump.isBttvEmotesEnabled() ? View.VISIBLE :
                                                                    View.GONE);
    }

    public static Fragment getModSettingsFragment() {
        return new MainSettingsFragment();
    }

    public static void setupClicker(final ICommunityPointsButtonViewDelegate view) {
        if (!PreferenceManager.INSTANCE.isAutoclickerEnabled()) {
            return;
        }

        if (view == null) {
            Logger.error("view is null");
            return;
        }

        view.maybeClickOnBonus();
    }

    public static void maybeShowModInfoBanner(final FragmentActivity fragmentActivity,
                                              final TwitchAccountManager accountManager) {
        if (PreferenceManager.INSTANCE.getLastBuildNumber() == LoaderLS.getBuildNumber())
            return;

        if (PreferenceManager.INSTANCE.isBannerShown())
            return;

        FragmentUtil.showDialogFragment(fragmentActivity, new ModInfoBannerFragment(), "modInfo");
    }

    public static boolean onPreferenceStartFragment(FragmentActivity fragmentActivity,
                                             PreferenceFragmentCompat caller, Preference pref) {
        if (fragmentActivity == null) {
            Logger.error("fragmentActivity is null");
            return false;
        }

        if (caller == null) {
            Logger.error("caller is null");
            return false;
        }

        if (pref == null) {
            Logger.error("pref is null");
            return false;
        }

        final Bundle args = pref.getExtras();
        final Fragment fragment = fragmentActivity.getSupportFragmentManager().getFragmentFactory()
                .instantiate(fragmentActivity.getClassLoader(), pref.getFragment());

        String tag = caller.getTag();
        if (fragment instanceof IPreferenceFragment) {
            tag = ((IPreferenceFragment) fragment).getFragmentTag();
        }

        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);

        tv.twitch.android.util.FragmentUtil.Companion.addOrRecreateFragment(fragmentActivity,
                fragment, tag, args);

        return true;
    }
}
