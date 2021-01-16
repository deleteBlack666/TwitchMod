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
import tv.twitch.android.mod.bridge.interfaces.ILiveChatSource;
import tv.twitch.android.mod.bridge.interfaces.IPreferenceFragment;
import tv.twitch.android.mod.bridge.interfaces.ISharedPanelWidget;
import tv.twitch.android.mod.libs.preference.Preference;
import tv.twitch.android.mod.libs.preference.PreferenceFragmentCompat;
import tv.twitch.android.mod.emote.EmoteManager;
import tv.twitch.android.mod.fragment.ModInfoBannerFragment;
import tv.twitch.android.mod.fragment.SleepTimerFragment;
import tv.twitch.android.mod.fragment.setting.MainSettingsFragment;
import tv.twitch.android.mod.setting.PreferenceManager;
import tv.twitch.android.mod.util.ClipDownloader;
import tv.twitch.android.mod.util.FragmentUtil;
import tv.twitch.android.mod.util.Logger;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.models.clips.ClipModel;
import tv.twitch.android.shared.chat.events.ChannelSetEvent;
import tv.twitch.android.shared.chat.events.ChatConnectionEvents;


public final class HookController {
    public static View setupPlayerLockButton(View container, final IBottomPlayerControlOverlayViewDelegate delegate) {
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

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.INSTANCE.setLockSwiper(!PreferenceManager.INSTANCE.shouldLockSwiper());
                delegate.updateLockButtonState();
            }
        });

        return buttonView;
    }

    public static void updatePlayerLockButtonState(ImageView lockButton) {
        if (lockButton == null) {
            return;
        }

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

        lockButton.setImageResource(PreferenceManager.INSTANCE.shouldLockSwiper() ? unlockDrawableId : lockDrawableId);
    }

    public static void changeLockButtonVisibility(ImageView lockButton, boolean z) {
        if (lockButton == null) {
            Logger.error("lockButton is null");
            return;
        }

        if (!(PreferenceManager.INSTANCE.isVolumeSwiperEnabled() ||
              PreferenceManager.INSTANCE.isBrightnessSwiperEnabled()) ||
            !PreferenceManager.INSTANCE.shouldShowLockButton()) {
            lockButton.setVisibility(View.GONE);
            return;
        }

        lockButton.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public static View setupPlayerRefreshButton(View container,
                                                final IBottomPlayerControlOverlayViewDelegate delegate) {
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

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.clickRefresh();
            }
        });

        return refreshButton;
    }

    public static View setupPlayerUptime(View container,
                                         final IBottomPlayerControlOverlayViewDelegate delegate) {
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

    public static View setupPlayerUptimeIcon(View container,
                                             final IBottomPlayerControlOverlayViewDelegate delegate) {
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

        if (panelWidget == null) {
            Logger.error("panelWidget is null");
            return;
        }

        downloadButton.setOnClickListener(new ClipDownloader(panelWidget));
    }

    public static void setupPlayerSleepTimerButton(final Context context, ImageView button) {
        if (context == null) {
            Logger.error("context is null");
            return;
        }

        if (button == null) {
            Logger.error("button is null");
            return;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.showDialogFragment(context, new SleepTimerFragment(), "sleepTimerPicker");
            }
        });
    }

    public static View getSleepTimerButton(View container) {
        if (container == null) {
            Logger.error("container is null");
            return null;
        }

        int buttonId = ResourcesManager.getId("sleep_timer_button");
        if (buttonId == 0) {
            Logger.error("buttonId == 0");
            return null;
        }

        return container.findViewById(buttonId);
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

        bttvEmotesButton.setVisibility(PreferenceManager.INSTANCE.showBttvEmotesInChat() ? View.VISIBLE :
                View.GONE);
    }

    public static Fragment getModSettingsFragment() {
        return new MainSettingsFragment();
    }

    public static void setupPointClicker(final ICommunityPointsButtonViewDelegate view) {
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

    public static void onSleepTimeChanged(int hour, int minute) {
        LoaderLS.getInstance().onTimeChanged(hour, minute);
    }

    public static void injectRecentMessages(ChatConnectionEvents chatConnectionEvent,
                                            final ILiveChatSource liveChatSource, ChannelInfo channel) {
        if (chatConnectionEvent instanceof ChatConnectionEvents.ChatConnectingEvent) {
            if (channel != null && channel.getId() == chatConnectionEvent.getChannelId()) {
                Hook.injectRecentMessages(liveChatSource, channel);
            }
        }
    }

    public static void setCurrentChannel(ChannelSetEvent event) {
        if (event == null) {
            Logger.error("event is null");
            return;
        }

        ChannelInfo channelInfo = event.getChannelInfo();
        if (channelInfo == null) {
            Logger.error("channelInfo is null");
            return;
        }

        EmoteManager.INSTANCE.setCurrentChannel(channelInfo.getId());
    }
}
