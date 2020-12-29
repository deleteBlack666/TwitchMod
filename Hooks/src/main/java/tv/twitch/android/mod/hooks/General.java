package tv.twitch.android.mod.hooks;


import android.content.Context;
import android.graphics.Color;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.PlaybackParameters;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import okhttp3.Request;
import tv.twitch.android.api.parsers.PlayableModelParser;
import tv.twitch.android.core.user.TwitchAccountManager;
import tv.twitch.android.mod.bridges.ChatFactory;
import tv.twitch.android.mod.bridges.LoaderLS;
import tv.twitch.android.mod.bridges.ResourcesManager;
import tv.twitch.android.mod.bridges.interfaces.IChatConnectionController;
import tv.twitch.android.mod.bridges.interfaces.IChatMessageFactory;
import tv.twitch.android.mod.bridges.interfaces.ICommunityPointsButtonViewDelegate;
import tv.twitch.android.mod.bridges.interfaces.ILiveChatSource;
import tv.twitch.android.mod.bridges.interfaces.IUrlDrawable;
import tv.twitch.android.mod.bridges.models.EmoteSet;
import tv.twitch.android.mod.bridges.models.EmoteUiModelWithUrl;
import tv.twitch.android.mod.emotes.EmoteManager;
import tv.twitch.android.mod.models.chat.Emote;
import tv.twitch.android.mod.models.preferences.Gifs;
import tv.twitch.android.mod.models.preferences.MsgDelete;
import tv.twitch.android.mod.models.preferences.PlayerImpl;
import tv.twitch.android.mod.settings.PreferenceManager;
import tv.twitch.android.mod.utils.ChatMesssageFilteringUtil;
import tv.twitch.android.mod.utils.ChatUtil;
import tv.twitch.android.mod.utils.DateUtil;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.mod.utils.Logger;
import tv.twitch.android.mod.utils.ViewUtil;
import tv.twitch.android.models.Playable;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.models.chomments.ChommentModel;
import tv.twitch.android.models.streams.StreamModel;
import tv.twitch.android.shared.chat.ChatMessageInterface;
import tv.twitch.android.shared.chat.adapter.item.ChatMessageClickedEvents;
import tv.twitch.android.shared.chat.events.ChannelSetEvent;
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiModel;
import tv.twitch.android.shared.emotes.emotepicker.models.EmoteUiSet;
import tv.twitch.android.shared.experiments.Experiment;
import tv.twitch.android.util.EmoteUrlUtil;
import tv.twitch.chat.ChatEmoticonSet;
import tv.twitch.chat.ChatLiveMessage;
import tv.twitch.chat.ChatMessageInfo;


public final class General {
    private final static String VOD_PLAYER_PRESENTER_CLASS = "tv.twitch.android.shared.player.presenters.VodPlayerPresenter";

    private final static String PLAYER_CORE = "playercore";
    private final static String PLAYER_EXO2 = "exoplayer_2";
    private final static int DEFAULT_FONT_SIZE = 13;
    private final static String FAKE_PACKAGE_NAME = "tv.twitch.android.app";
    private final static String FAKE_PLAYER_TYPE = "embed";
    private static final int HIGHLIGHT_COLOR = Color.argb(100, 255, 0, 0);

    public static void tryPatchAdRequest(Request request, Request.Builder builder) {
        if (!PreferenceManager.INSTANCE.isSurestreamAdblockOn()) {
            return;
        }

        if (!Helper.isAccessTokenRequest(request)) {
            return;
        }

        String authorization = LoaderLS.getAuthorization();
        if (!TextUtils.isEmpty(authorization)) {
            builder.removeHeader("Authorization");
            builder.addHeader("Authorization", LoaderLS.getAuthorization());
        }
    }

    public static void maybeInvalidateChatItem(WeakReference<View> viewWeakReference,
                                               IUrlDrawable urlDrawable) {
        if (!PreferenceManager.INSTANCE.fixWideEmotes()) {
            return;
        }

        if (urlDrawable == null) {
            return;
        }

        if (viewWeakReference == null) {
            return;
        }

        if (urlDrawable.isBadge() || urlDrawable.isTwitchEmote()) {
            return;
        }

        View view = viewWeakReference.get();
        if (view == null) {
            return;
        }

        view.invalidate();
        view.requestLayout();
        viewWeakReference.clear();
    }

    public static void requestEmotes(ChannelInfo channelInfo) {
        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat()) {
            return;
        }

        EmoteManager.INSTANCE.fetchGlobalEmotes();
        EmoteManager.INSTANCE.requestRoomEmotes(channelInfo.getId());
    }

    public static void requestEmotes(final PlayableModelParser playableModelParser, Playable playable) {
        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat()) {
            return;
        }

        EmoteManager.INSTANCE.fetchGlobalEmotes();
        EmoteManager.INSTANCE.requestRoomEmotes(Helper.getChannelId(playableModelParser, playable));
    }

    public static ChatEmoticonSet[] hookChatEmoticonSet(ChatEmoticonSet[] orgSet) {
        if (orgSet == null) {
            return null;
        }

        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat()) {
            return orgSet;
        }

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

    public static boolean shouldHighlightMessage(ChatMessageInfo messageInfo,
                                                 TwitchAccountManager accountManager) {
        if (!PreferenceManager.INSTANCE.isHighlightingEnabled()) {
            return false;
        }

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

    public static int calcSteamUptime(StreamModel streamModel) {
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

    public static SpannedString hookChatMessage(IChatMessageFactory factory,
                                                ChatMessageInterface chatMessageInterface,
                                                SpannedString orgMessage, int channelId) {
        PreferenceManager manager = PreferenceManager.INSTANCE;

        if (TextUtils.isEmpty(orgMessage)) {
            return orgMessage;
        }

        if (chatMessageInterface.isDeleted()) {
            return orgMessage;
        }

        SpannedString hooked = new SpannedString(orgMessage);

        if (manager.showBttvEmotesInChat()) {
            hooked = ChatUtil.tryAddEmotes(factory, chatMessageInterface, hooked, channelId,
                    manager.getGifsStrategy().equals(Gifs.DISABLED), manager.getEmoteSize());
        }

        return hooked;
    }

    public static SpannedString hookChatMessageBadges(IChatMessageFactory factory,
                                                      ChatMessageInterface chatMessageInterface,
                                                      SpannedString badges) {
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

    public static Spanned hookMarkAsDeleted(tv.twitch.android.shared.chat.util.ChatUtil.Companion companion,
                                            Spanned msg, Context context,
                                            PublishSubject<ChatMessageClickedEvents> publishSubject,
                                            boolean hasModAccess) {
        if (companion == null) {
            Logger.error("companion is null");
            return msg;
        }

        if (msg == null) {
            Logger.error("msg is null");
            return msg;
        }

        if (context == null) {
            Logger.error("context is null");
            return msg;
        }

        switch (PreferenceManager.INSTANCE.getMsgDelete()) {
            default:
            case MsgDelete.DEFAULT:
                return companion.createDeletedSpanFromChatMessageSpan(msg, context, publishSubject,
                        hasModAccess);
            case MsgDelete.MOD:
                return companion.createDeletedSpanFromChatMessageSpan(msg, context, publishSubject,
                        true);
            case MsgDelete.STRIKETHROUGH:
                return ChatUtil.createDeletedStrikethroughSpanFromChatMessageSpan(msg);
        }
    }

    public static String hookSetName(String org, String setId) {
        EmoteSet set = EmoteSet.findById(setId);
        if (set != null) {
            return set.getTitle();
        }

        return org;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static String hookExperimentalGroup(Experiment experiment, String org) {
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
    public static boolean hookExperimental(Experiment experiment, boolean org) {
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

    public static PlaybackParameters hookVodPlayerStandaloneMediaClockInit() {
        if (Helper.isOnStackTrace(VOD_PLAYER_PRESENTER_CLASS)) {
            PreferenceManager preferenceManager = PreferenceManager.INSTANCE;
            return new PlaybackParameters(preferenceManager.getExoplayerSpeed());
        }

        return PlaybackParameters.DEFAULT;
    }

    public static Spanned addTimestampToMessage(Spanned message, int userId) {
        if (!PreferenceManager.INSTANCE.isChatTimestampsEnabled()) {
            return message;
        }

        if (message == null) {
            return null;
        }

        if (userId <= 0) {
            return message;
        }

        return ChatUtil.createTimestampSpanFromChatMessageSpan(message, new Date());
    }

    public static boolean hookFollowedGamesFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowGameSection()) {
            return org;
        }

        return false;
    }

    public static boolean hookRecommendedStreamsFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowRecommendationSection()) {
            return org;
        }

        return false;
    }

    public static boolean hookResumeWatchingFetcher(boolean org) {
        if (!PreferenceManager.INSTANCE.hideFollowResumeSection()) {
            return org;
        }

        return false;
    }

    public static int hookMiniPlayerWidth(int size) {
        PreferenceManager preferenceManager = PreferenceManager.INSTANCE;
        return (int) (preferenceManager.getMiniPlayerScale() * size);
    }

    public static ChatLiveMessage[] hookReceivedMessages(IChatConnectionController connectionController,
                                                         ChatLiveMessage[] chatLiveMessageArr) {
        if (chatLiveMessageArr == null || chatLiveMessageArr.length == 0) {
            return chatLiveMessageArr;
        }

        ChatMesssageFilteringUtil filteringUtil = ChatMesssageFilteringUtil.INSTANCE;
        if (!filteringUtil.isEmpty()) {
            chatLiveMessageArr = filteringUtil.filterLiveMessages(chatLiveMessageArr);
        }

        if (connectionController == null) {
            Logger.error("connectionController is null");
            return chatLiveMessageArr;
        }

        int viewerId = connectionController.getViewerId();
        if (viewerId > 0) {
            chatLiveMessageArr = filteringUtil.filterByMessageLevel(chatLiveMessageArr, viewerId,
                    PreferenceManager.INSTANCE.getFilterMessageLevel());
        }

        return chatLiveMessageArr;
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

    public static int hookUsernameSpanColor(int usernameColor) {
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

        if (!PreferenceManager.INSTANCE.showMessageHistory()) {
            return;
        }

        ChatUtil.tryAddRecentMessages(source, channelInfo, PreferenceManager.INSTANCE.getMessageHistoryLimit());
    }

    public static List<EmoteUiSet> hookEmotePickerSet(List<EmoteUiSet> emoteUiSets, Integer channelId) {
        if (emoteUiSets == null) {
            return null;
        }

        if (!PreferenceManager.INSTANCE.showBttvEmotesInChat()) {
            return emoteUiSets;
        }

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

    public static ChommentModel maybeFilterThisChomment(ChommentModel model) {
        if (model == null) {
            return null;
        }

        if (ChatMesssageFilteringUtil.INSTANCE.filterChomment(model)) {
            return model;
        }

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

        if (fontSize == DEFAULT_FONT_SIZE) {
            return;
        }

        textView.setTextSize(fontSize);
    }

    public static float hookMediaSpanDpSize(float size) {
       int fontSize = PreferenceManager.INSTANCE.getChatMessageFontSize();

        if (fontSize == DEFAULT_FONT_SIZE) {
            return size;
        }

        float scale = (float) fontSize / DEFAULT_FONT_SIZE;

        return Math.round(size * scale);
    }

    public static Spanned addVodTimestampToMessages(Spanned message, ChommentModel chommentModel) {
        if (message == null) {
            return null;
        }

        if (chommentModel == null) {
            return null;
        }

        if (!PreferenceManager.INSTANCE.isChatTimestampsVodEnabled()) {
            return message;
        }

        return ChatUtil.createOffsetTimestampSpanFromChatMessageSpan(message, chommentModel.getContentOffsetSeconds());
    }

    public static String hookPackageName(String org) { // TODO: __HOOK
        return FAKE_PACKAGE_NAME;
    }

    public static void setLockSwiper(boolean z) {
        PreferenceManager.INSTANCE.setLockSwiper(z);
    }

    public static int hookSetupForLandscapeDefaultChatScreenEdgePercentage(int org) {
        return PreferenceManager.INSTANCE.getLandscapeChatScale();
    }

    public static int hookSetupForLandscapeSplitViewChatScreenEdgePercentage(int org) {
        return PreferenceManager.INSTANCE.getLandscapeChatScaleMax();
    }

    public static void highlightView(View view, boolean isHighlighted) {
        ViewUtil.setBackground(view, isHighlighted ? HIGHLIGHT_COLOR : null);
    }

    public static void helper() {
        float res = hookMediaSpanDpSize(0); // TODO: __HOOK
        String pkg = hookPackageName(""); // TODO: __HOOK
    }

    public static int hookPlayerMetadataViewId(int org) {
        if (!PreferenceManager.INSTANCE.isCompactPlayerFollowViewEnabled())
            return org;

        int id = ResourcesManager.getLayoutId("player_metadata_view_extended_mod");
        if (id == 0) {
            Logger.error("layout not found");
            return org;
        }

        return id;
    }
}
