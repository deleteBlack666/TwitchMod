package tv.twitch.android.mod.bridges;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.PlaybackParameters;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridges.interfaces.IBottomPlayerControlOverlayViewDelegate;
import tv.twitch.android.mod.bridges.interfaces.IChatConnectionController;
import tv.twitch.android.mod.bridges.interfaces.ICommunityPointsButtonViewDelegate;
import tv.twitch.android.mod.bridges.interfaces.IEmotePickerViewDelegate;
import tv.twitch.android.mod.bridges.interfaces.ILiveChatSource;
import tv.twitch.android.mod.bridges.interfaces.ISharedPanelWidget;
import tv.twitch.android.mod.bridges.interfaces.IUrlDrawable;
import tv.twitch.android.mod.fragments.SleepTimerFragment;
import tv.twitch.android.mod.fragments.settings.MainSettingsFragment;
import tv.twitch.android.mod.models.preferences.MsgDelete;
import tv.twitch.android.mod.utils.ClipDownloader;
import tv.twitch.android.mod.utils.DateUtil;
import tv.twitch.android.mod.utils.FragmentUtil;
import tv.twitch.android.mod.utils.ViewUtil;
import tv.twitch.android.models.chomments.ChommentModel;
import tv.twitch.android.models.streams.StreamModel;
import tv.twitch.android.shared.chat.adapter.item.ChatMessageClickedEvents;
import tv.twitch.android.shared.chat.events.ChannelSetEvent;
import tv.twitch.android.shared.chat.ChatMessageInterface;
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiModel;
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiSet;
import tv.twitch.android.shared.experiments.Experiment;
import tv.twitch.android.api.parsers.PlayableModelParser;
import tv.twitch.android.mod.bridges.interfaces.IChatMessageFactory;
import tv.twitch.android.mod.bridges.models.EmoteSet;
import tv.twitch.android.mod.bridges.models.EmoteUiModelWithUrl;
import tv.twitch.android.mod.emotes.EmoteManager;
import tv.twitch.android.mod.models.chat.Emote;
import tv.twitch.android.mod.models.preferences.Gifs;
import tv.twitch.android.mod.models.preferences.PlayerImpl;
import tv.twitch.android.mod.settings.PreferenceManager;
import tv.twitch.android.mod.utils.ChatMesssageFilteringUtil;
import tv.twitch.android.mod.utils.ChatUtil;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.mod.utils.Logger;
import tv.twitch.android.models.Playable;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.shared.ui.elements.bottomsheet.InteractiveRowView;
import tv.twitch.android.util.EmoteUrlUtil;
import tv.twitch.chat.ChatEmoticonSet;
import tv.twitch.chat.ChatLiveMessage;
import tv.twitch.chat.ChatMessageInfo;


@SuppressWarnings({"FinalStaticMethod"})
public class Hooks {
    private final static String VOD_PLAYER_PRESENTER_CLASS = "tv.twitch.android.shared.player.presenters.VodPlayerPresenter";
    private final static String PLAYER_CORE = "playercore";
    private final static String PLAYER_EXO2 = "exoplayer_2";
    private final static float DEFAULT_PLAYBACK_SPEED = 1.0f;
    private final static int DEFAULT_FONT_SIZE = 13;
    private final static String FAKE_PACKAGE_NAME = "tv.twitch.android.app";
    private final static String FAKE_PLAYER_TYPE = "embed";

    private static final int HIGHLIGHT_COLOR = Color.argb(100, 255, 0, 0);


    public final static String hookSetName(String org, String setId) {
        EmoteSet set = EmoteSet.findById(setId);
        if (set != null)
            return set.getTitle();

        return org;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public final static String hookExperimentalGroup(Experiment experiment, String org) {
        if (experiment == null) {
            Logger.error("experimental is null");
            return org;
        }

        switch (experiment) {
            case VIDEOPLAYER_SELECTION:
                switch (PreferenceManager.INSTANCE.getPlayerImplementation()) {
                    default:
                    case PlayerImpl.AUTO:
                        return org;
                    case PlayerImpl.CORE:
                        return PLAYER_CORE;
                    case PlayerImpl.EXO:
                        return PLAYER_EXO2;
                }
        }

        return org;
    }

    @SuppressWarnings("SimplifiableConditionalExpression")
    public final static boolean hookExperimental(Experiment experiment, boolean org) {
        if (experiment == null) {
            Logger.error("experiment is null");
            return org;
        }

        switch (experiment) {
            case UPDATE_PROMPT_ROLLOUT:
                return false;

            case COMMUNITY_POINTS_PREDICTIONS:
            case CREATOR_SETTINGS_MENU:
                return true;

            case MGST_DISABLE_PRE_ROLLS:
                return PreferenceManager.INSTANCE.isPlayerAdblockOn() ? true : org;

            case HYPETRAIN:
                return PreferenceManager.INSTANCE.shouldShowHypeTrain();

            case CHAT_INPUT_FOLLOWER:
            case CHAT_INPUT_VERIFIED:
            case CHAT_INPUT_SUBSCRIBER:
                return PreferenceManager.INSTANCE.hideChatRestriction() ? false : org;

            case VAES_OM:
            case GRANDDADS:
            case SURESTREAM_OM:
            case SURESTREAM_ADS_PBYP:
            case ADS_PBYP:
            case MULTIPLAYER_ADS:
            case VAES_DEVICE_TARGETING_PARAMETERS:
            case AMAZON_IDENTITY_INTEGRATION:
                return PreferenceManager.INSTANCE.isPlayerAdblockOn() ? false : org;

            case FLOATING_CHAT:
                return PreferenceManager.INSTANCE.showFloatingChat();

            case NEW_EMOTE_PICKER:
                return PreferenceManager.INSTANCE.forceOldEmotePickerView() ? false : org;
        }

        return org;
    }

    public final static PlaybackParameters hookVodPlayerStandaloneMediaClockInit() {
        if (Helper.isOnStackTrace(VOD_PLAYER_PRESENTER_CLASS)) {
            PreferenceManager preferenceManager = PreferenceManager.INSTANCE;
            return new PlaybackParameters(preferenceManager.getExoplayerSpeed());
        }

        return PlaybackParameters.DEFAULT;
    }

    public final static boolean shouldHighlightMessage(ChatMessageInfo messageInfo, TwitchAccountManager accountManager) {
        if (!PreferenceManager.INSTANCE.isHighlightingEnabled())
            return false;

        if (accountManager == null) {
            Logger.error("accountManager is null");
            return false;
        }

        if (messageInfo == null) {
            Logger.error("messageInfo is null");
            return false;
        }

        return ChatUtil.isMentionUser(messageInfo, accountManager.getUsername());
    }

    public final static Spanned addTimestampToMessage(Spanned message, int userId) {
        if (message == null)
            return null;

        if (!PreferenceManager.INSTANCE.isChatTimestampsEnabled())
            return message;

        if (userId <= 0)
            return message;

        return ChatUtil.addTimestamp(message, new Date());
    }

    public final static ChatEmoticonSet[] hookChatEmoticonSet(ChatEmoticonSet[] orgSet) {
        if (orgSet == null)
            return null;

        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat())
            return orgSet;

        Collection<Emote> globalEmotes = EmoteManager.INSTANCE.getGlobalEmotes();
        Collection<Emote> bttvEmotes = EmoteManager.INSTANCE.getBttvEmotesForCurrentChannel();
        Collection<Emote> ffzEmotes = EmoteManager.INSTANCE.getFfzEmotesForCurrentChannel();

        ChatEmoticonSet[] newSet = new ChatEmoticonSet[orgSet.length+3];
        System.arraycopy(orgSet, 0, newSet, 0, orgSet.length);

        newSet[newSet.length-3] = ChatFactory.getSet(EmoteSet.FFZ.getId(), ffzEmotes);
        newSet[newSet.length-2] = ChatFactory.getSet(EmoteSet.BTTV.getId(), bttvEmotes);
        newSet[newSet.length-1] = ChatFactory.getSet(EmoteSet.GLOBAL.getId(), globalEmotes);

        return newSet;
    }

    public final static void requestEmotes(ChannelInfo channelInfo) {
        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat())
            return;

        EmoteManager.INSTANCE.fetchGlobalEmotes();
        EmoteManager.INSTANCE.requestRoomEmotes(channelInfo.getId());
    }

    public final static void requestEmotes(final PlayableModelParser playableModelParser, Playable playable) {
        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat())
            return;

        EmoteManager.INSTANCE.fetchGlobalEmotes();
        EmoteManager.INSTANCE.requestRoomEmotes(Helper.getChannelId(playableModelParser, playable));
    }

    public final static boolean shouldShowStatButton() {
        return PreferenceManager.INSTANCE.shouldShowPlayerStatButton();
    }

    public final static boolean shouldShowRefreshButton() {
        return PreferenceManager.INSTANCE.shouldShowPlayerRefreshButton();
    }

    public final static boolean shouldShowLockButton() {
        return PreferenceManager.INSTANCE.shouldShowLockButton();
    }

    public final static boolean isSwipperEnabled() {
        return PreferenceManager.INSTANCE.isVolumeSwiperEnabled() || PreferenceManager.INSTANCE.isBrightnessSwiperEnabled();
    }

    public final static boolean shouldLockSwiper() {
        return PreferenceManager.INSTANCE.shouldLockSwiper();
    }

    public final static void setLockSwiper(boolean z) {
        PreferenceManager.INSTANCE.setLockSwiper(z);
    }

    public final static boolean isFollowedGamesFetcherJump() {
        return PreferenceManager.INSTANCE.hideFollowGameSection();
    }

    public final static boolean isRecommendedStreamsFetcher() {
        return PreferenceManager.INSTANCE.hideFollowRecommendationSection();
    }

    public final static boolean isResumeWatchingFetcher() {
        return PreferenceManager.INSTANCE.hideFollowResumeSection();
    }

    public final static boolean hookFollowedGamesFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowGameSection())
            return org;

        return false;
    }

    public final static boolean hookRecommendedStreamsFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowRecommendationSection())
            return org;

        return false;
    }

    public final static boolean hookResumeWatchingFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowResumeSection())
            return org;

        return false;
    }

    public final static int hookMiniPlayerWidth(int size) {
        PreferenceManager preferenceManager = PreferenceManager.INSTANCE;
        return (int) (preferenceManager.getMiniPlayerScale() * size);
    }

    public final static boolean isJumpDisRecentSearch() {
        return PreferenceManager.INSTANCE.hideRecentSearchResult();
    }

    public final static boolean isPlayerMetadataJump() {
        return PreferenceManager.INSTANCE.isCompactPlayerFollowViewEnabled();
    }

    public final static int hookPlayerMetadataViewId(int org) {
        if (!PreferenceManager.INSTANCE.isCompactPlayerFollowViewEnabled())
            return org;

        int id = ResourcesManager.getLayoutId("player_metadata_view_extended_mod");
        if (id == 0) {
            Logger.error("layout not found");
            return org;
        }

        return id;
    }

    public final static boolean isDevModeOn() {
        return PreferenceManager.INSTANCE.isDevModeOn();
    }

    public final static boolean isAdblockOn() {
        return PreferenceManager.INSTANCE.isPlayerAdblockOn();
    }

    public final static boolean isInterceptorOn() {
        return PreferenceManager.INSTANCE.isInterceptorEnabled();
    }

    public final static ChatLiveMessage[] hookReceivedMessages(IChatConnectionController connectionController, ChatLiveMessage[] chatLiveMessageArr) {
        if (chatLiveMessageArr == null || chatLiveMessageArr.length == 0)
            return chatLiveMessageArr;

        ChatMesssageFilteringUtil filteringUtil = ChatMesssageFilteringUtil.INSTANCE;
        if (filteringUtil.isEmpty())
            return chatLiveMessageArr;

        chatLiveMessageArr = filteringUtil.filterLiveMessages(chatLiveMessageArr);

        if (connectionController == null) {
            Logger.error("connectionController is null");
            return chatLiveMessageArr;
        }

        int viewerId = connectionController.getViewerId();
        if (viewerId > 0) {
            chatLiveMessageArr = filteringUtil.filterByMessageLevel(chatLiveMessageArr, viewerId, PreferenceManager.INSTANCE.getFilterMessageLevel());
        }

        return chatLiveMessageArr;
    }

    public final static boolean isJumpDisableAutoplay() {
        return PreferenceManager.INSTANCE.disableTheatreAutoplay();
    }

    public final static void setCurrentChannel(ChannelSetEvent event) {
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

    public final static boolean isHideDiscoverTab() {
        return PreferenceManager.INSTANCE.hideDiscoverTab();
    }

    public final static boolean isHideEsportsTab() {
        return PreferenceManager.INSTANCE.hideEsportsTab();
    }

    public final static boolean isGifsEnabled() {
        return PreferenceManager.INSTANCE.isGifsAnimated();
    }

    public final static int hookUsernameSpanColor(int usernameColor) {
        return ChatUtil.fixUsernameColor(usernameColor, PreferenceManager.INSTANCE.isDarkThemeEnabled());
    }

    public static int getFloatingChatQueueSize() {
        return PreferenceManager.INSTANCE.getFloatingChatQueueSize();
    }

    public static void injectRecentMessages(final ILiveChatSource source, final ChannelInfo channelInfo) {
        if (source == null) {
            Logger.error("source is null");
            return;
        }

        if (channelInfo == null) {
            Logger.error("channelInfo is null");
            return;
        }

        if (!PreferenceManager.INSTANCE.showMessageHistory())
            return;

        ChatUtil.tryAddRecentMessages(source, channelInfo, PreferenceManager.INSTANCE.getMessageHistoryLimit());
    }

    public static Spanned hookMarkAsDeleted(tv.twitch.android.shared.chat.util.ChatUtil.Companion companion, Spanned msg, Context context, PublishSubject<ChatMessageClickedEvents> publishSubject, boolean hasModAccess) {
        if (TextUtils.isEmpty(msg)) {
            Logger.error("empty msg");
            return msg;
        }

        switch (PreferenceManager.INSTANCE.getMsgDelete()) {
            default:
            case MsgDelete.DEFAULT:
                return companion.createDeletedSpanFromChatMessageSpan(msg, context, publishSubject, hasModAccess);
            case MsgDelete.MOD:
                return companion.createDeletedSpanFromChatMessageSpan(msg, context, publishSubject, true);
            case MsgDelete.STRIKETHROUGH:
                return ChatUtil.tryAddStrikethrough(msg);
        }
    }

    public static SpannedString hookBadges(IChatMessageFactory factory, ChatMessageInterface chatMessageInterface, SpannedString badges) {
        if (factory == null) {
            Logger.error("factory is null");
            return badges;
        }

        if (chatMessageInterface == null) {
            Logger.error("chatMessageInterface is null");
            return badges;
        }

        if (badges == null) {
            Logger.error("badges is null");
            return badges;
        }

        return badges;
    }

    public static SpannedString hookChatMessage(IChatMessageFactory factory, ChatMessageInterface chatMessageInterface, SpannedString orgMessage, int channelId) {
        PreferenceManager manager = PreferenceManager.INSTANCE;

        if (TextUtils.isEmpty(orgMessage))
            return orgMessage;

        if (chatMessageInterface.isDeleted())
            return orgMessage;

        try {
            SpannedString hooked = orgMessage;

            if (manager.showBttvEmotesInChat())
                hooked = ChatUtil.tryAddEmotes(factory, chatMessageInterface, hooked, channelId,
                        manager.getGifsStrategy().equals(Gifs.DISABLED), manager.getEmoteSize());

            return hooked;
        } catch (Throwable th) {
            th.printStackTrace();
        }

        return orgMessage;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isJumpSystemIgnore() {
        return PreferenceManager.INSTANCE.hideSystemMessagesInChat();
    }

    public static List<EmoteUiSet> hookEmotePickerSet(List<EmoteUiSet> emoteUiSets, Integer channelId) {
        if (emoteUiSets == null)
            return null;

        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat())
            return emoteUiSets;

        if (channelId != null && channelId > 0) {
            Collection<Emote> ffzChannelEmotes = EmoteManager.INSTANCE.getFfzEmotes(channelId);
            if (!ffzChannelEmotes.isEmpty()) {
                Integer resId = ResourcesManager.getStringId(EmoteSet.FFZ.getTitleResId());
                emoteUiSets.add(ChatFactory.getEmoteUiSet(ffzChannelEmotes, resId));
            }

            Collection<Emote> bttvChannelEmotes = EmoteManager.INSTANCE.getBttvEmotes(channelId);
            if (!bttvChannelEmotes.isEmpty()) {
                Integer resId = ResourcesManager.getStringId(EmoteSet.BTTV.getTitleResId());
                emoteUiSets.add(ChatFactory.getEmoteUiSet(bttvChannelEmotes, resId));
            }
        }

        Collection<Emote> bttvGlobalEmotes = EmoteManager.INSTANCE.getGlobalEmotes();
        if (!bttvGlobalEmotes.isEmpty()) {
            Integer resId = ResourcesManager.getStringId(EmoteSet.GLOBAL.getTitleResId());
            emoteUiSets.add(ChatFactory.getEmoteUiSet(bttvGlobalEmotes, resId));
        }

        return emoteUiSets;
    }

    public static String hookGetEmoteUrl(Context context, EmoteUiModel emoteUiModel) {
        if (emoteUiModel instanceof EmoteUiModelWithUrl) {
            return ((EmoteUiModelWithUrl) emoteUiModel).getUrl();
        }

        return EmoteUrlUtil.getEmoteUrl(context, emoteUiModel.getId());
    }

    public static int hookSetupForLandscapeDefaultChatScreenEdgePercentage(int org) {
        return PreferenceManager.INSTANCE.getLandscapeChatScale();
    }

    public static int hookSetupForLandscapeSplitViewChatScreenEdgePercentage(int org) {
        return PreferenceManager.INSTANCE.getLandscapeChatScaleMax();
    }

    public static boolean isBypassChatBanJump() {
        return PreferenceManager.INSTANCE.showChatForBannedUser();
    }

    public final static void helper() {
        float res = Hooks.hookMediaSpanDpSize(0); // TODO: __HOOK
        String pkg = Hooks.hookPackageName(""); // TODO: __HOOK
    }

    public static boolean isSupportWideEmotes() {
        return PreferenceManager.INSTANCE.fixWideEmotes();
    }

    public static boolean isDisableGoogleBillingJump() {
        return PreferenceManager.INSTANCE.isGoogleBillingDisabled();
    }

    @SuppressWarnings({"SwitchStatementWithTooFewBranches", "SimplifiableConditionalExpression"})
    public static boolean hookExperimentalMultiVariant(Experiment experiment, String str, boolean org) {
        if (experiment == null) {
            Logger.error("experimental is null");
            return org;
        }

        if (str == null) {
            Logger.error("str is null");
            return org;
        }

        if (str.isEmpty()) {
            Logger.warning("empty str");
            return org;
        }

        switch (experiment) {
            case CLIPFINITY:
                switch (str) {
                    case "control":
                        return PreferenceManager.INSTANCE.disableClipfinity() ? true : org;
                    case "active_with_entry_points":
                        return PreferenceManager.INSTANCE.disableClipfinity() ? false : org;
                    default:
                        return org;
            }
        }

        return org;
    }

    public static void maybeInvalidateContainerView(WeakReference<View> viewWeakReference, IUrlDrawable urlDrawable) {
        if (urlDrawable == null)
            return;

        if (viewWeakReference == null)
            return;

        if (!PreferenceManager.INSTANCE.fixWideEmotes())
            return;

        if (urlDrawable.isBadge() || urlDrawable.isTwitchEmote())
            return;

        View view = viewWeakReference.get();
        if (view == null) {
            Logger.debug("view is null");
            return;
        }

        view.invalidate();
        view.requestLayout();
        viewWeakReference.clear();
    }

    public static boolean isMentionHighlightingEnabled() {
        return PreferenceManager.INSTANCE.isHighlightingEnabled();
    }

    public static void highlightView(View view, boolean isHighlighted) {
        ViewUtil.setBackground(view, isHighlighted ? HIGHLIGHT_COLOR : null);
    }

    public static ChommentModel maybeFilterThisChomment(ChommentModel model) {
        if (model == null)
            return null;

        if (ChatMesssageFilteringUtil.INSTANCE.filterChomment(model))
            return model;

        return null;
    }

    public static int getRewindSeek() {
        return -PreferenceManager.INSTANCE.getPlayerBackwardSeek();
    }

    public static int getForwardSeek() {
        return PreferenceManager.INSTANCE.getPlayerForwardSeek();
    }

    public static void setChatMessageFontSize(TextView textView) {
        if (textView == null) {
            Logger.error("textView is null");
            return;
        }

        int fontSize = PreferenceManager.INSTANCE.getChatMessageFontSize();

        if (fontSize == DEFAULT_FONT_SIZE)
            return;

        textView.setTextSize(fontSize);
    }

    public static float hookMediaSpanDpSize(float size) {
       int fontSize = PreferenceManager.INSTANCE.getChatMessageFontSize();

        if (fontSize == 13)
            return size;

        float scale = (float) fontSize / DEFAULT_FONT_SIZE;

        return Math.round(size * scale);
    }

    public static boolean isBttvEmotesEnabled() {
        return PreferenceManager.INSTANCE.showBttvEmotesInChat();
    }

    public static void setupClicker(final ICommunityPointsButtonViewDelegate view) {
        if (!PreferenceManager.INSTANCE.isAutoclickerEnabled())
            return;

        if (view == null) {
            Logger.error("view is null");
            return;
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                view.maybeClickOnBonus();
            }
        }, 5000);
    }

    public static void setupClipDownloader(InteractiveRowView downloadButton, ISharedPanelWidget panelWidget) {
        if (downloadButton == null) {
            Logger.error("downloadButton is null");
            return;
        }

        downloadButton.setOnClickListener(new ClipDownloader(panelWidget));
    }

    public static void setupLockButtonClickListener(View lockButton, final IBottomPlayerControlOverlayViewDelegate bottomPlayerControlOverlayViewDelegate) {
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hooks.shouldLockSwiper()) {
                    Hooks.setLockSwiper(false);
                    if (bottomPlayerControlOverlayViewDelegate != null)
                        bottomPlayerControlOverlayViewDelegate.updateLockButtonState();
                } else {
                    Hooks.setLockSwiper(true);
                    if (bottomPlayerControlOverlayViewDelegate != null)
                        bottomPlayerControlOverlayViewDelegate.updateLockButtonState();
                }
            }
        });

    }

    public static void setupRefreshButtonClickListener(View refreshButton, final IBottomPlayerControlOverlayViewDelegate bottomPlayerControlOverlayViewDelegate) {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomPlayerControlOverlayViewDelegate != null)
                    bottomPlayerControlOverlayViewDelegate.clickRefresh();
            }
        });
    }

    public static void setupBttvEmotesButtonClickListener(ImageView bttvEmotesButton, final IEmotePickerViewDelegate emotePickerViewDelegate) {
        bttvEmotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emotePickerViewDelegate != null)
                    emotePickerViewDelegate.scrollToBttvSection();
            }
        });
    }

    public static String hookUserAgentString(String org) {
        if (org == null)
            return null;

        if (org.contains("tv.twitchmod.android.app/"))
            org = org.replace("tv.twitchmod.android.app/", "tv.twitch.android.app/");

        return org;
    }

    public static void setupTimerSleepButton(final Context context, ImageView timerSleepButton) {
        if (timerSleepButton == null) {
            Logger.error("timerSleepButton is null");
            return;
        }

        timerSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.showDialogFragment(context, new SleepTimerFragment(), "timePicker");
            }
        });
    }

    public static Fragment getModSettingsFragment() {
        return new MainSettingsFragment();
    }

    public static Spanned addVodTimestampToMessages(Spanned message, ChommentModel chommentModel) {
        if (message == null)
            return null;

        if (chommentModel == null)
            return null;

        if (!PreferenceManager.INSTANCE.isChatTimestampsVodEnabled())
            return message;

        return ChatUtil.addOffsetTimestamp(message, chommentModel.getContentOffsetSeconds());
    }

    public static boolean shouldShowChatHeader() {
        return !PreferenceManager.INSTANCE.shouldHideChatHeaderContainer();
    }

    public static boolean isSurestreamAdblockOn() {
        return PreferenceManager.INSTANCE.isSurestreamAdblockV1On();
    }

    public static boolean isSurestreamAdblockV2On() {
        return PreferenceManager.INSTANCE.isSurestreamAdblockV2On();
    }

    public static int getStreamUptimeSeconds(StreamModel streamModel) {
        if (streamModel == null) {
            Logger.error("streamModel is null");
            return -1;
        }
        Date created = DateUtil.getStandardizeDateString(streamModel.getCreatedAt());
        if (created == null) {
            Logger.error("date is null");
            return -1;
        }

        long diff = new Date().getTime() - created.getTime();

        return (int) diff / 1000;
    }

    public static boolean shouldShowStreamUptime() {
        return PreferenceManager.INSTANCE.shouldShowStreamUptime();
    }

    public static String hookPlaybackAccessPlayerType(String org) {
        if (PreferenceManager.INSTANCE.isSurestreamAdblockV2On())
            return FAKE_PLAYER_TYPE;

        return org;
    }

    public static String hookPackageName(String org) { // TODO: __HOOK
        return FAKE_PACKAGE_NAME;
    }
}
