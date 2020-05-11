package tv.twitch.android.mod.bridges;


import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;

import com.google.android.exoplayer2.PlaybackParameters;

import java.util.Collection;
import java.util.Date;

import tv.twitch.a.k.c0.b.s.h;
import tv.twitch.a.k.g.z0.b;
import tv.twitch.android.api.i1.f1;
import tv.twitch.android.mod.emotes.EmoteManager;
import tv.twitch.android.mod.models.Emote;
import tv.twitch.android.mod.settings.PrefManager;
import tv.twitch.android.mod.utils.ChatUtils;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.mod.utils.Logger;
import tv.twitch.android.models.Playable;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.shared.chat.communitypoints.models.CommunityPointsModel;
import tv.twitch.chat.ChatEmoticonSet;


@SuppressWarnings("FinalStaticMethod")
public class Hooks {
    public static final String EXOPLAYER_CONSTANT = "exoplayer_2";

    /**
     * Class: *.*
     * signature: Ltv/twitch/a/k/c0/b/s/h;-><init>
     */
    public final static h hookUrlDrawableConstructor(h urlDrawableInstance) {
        if (urlDrawableInstance == null) {
            Logger.error("urlDrawableInstance is null");
            return null;
        }

        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        if (prefManager.isDisableGifs())
            return new StaticUrlDrawable(urlDrawableInstance);

        return urlDrawableInstance;
    }

    /**
     * Class: ChatMessageFactory
     * signature: private final CharSequence a(tv.twitch.a.k.g.g gVar, int i2, tv.twitch.a.k.g.t0.a aVar, boolean z, String str, String str2)
     */
    public final static int hookUsernameSpanColor(int usernameColor) {
        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        if (!prefManager.isFixBrightness() || !prefManager.isDarkTheme())
            return usernameColor;

        Helper helper = LoaderLS.getInstance().getHelper();
        return helper.getFineColor(usernameColor);
    }

    /**
     * Class: EmoteAdapterSection
     * signature: public void a(RecyclerView.b0 b0Var)
     */
    public static String hookSetName(String org, String setId) {
        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        if (!prefManager.isHookEmoticonSetOn())
            return org;

        // FIXME:
        if (!TextUtils.isEmpty(setId)) {
            if (setId.equals(ChatUtils.EmoteSet.GLOBAL.getId()))
                return "BetterTTV Global Emotes";
            else if (setId.equals(ChatUtils.EmoteSet.FFZ.getId()))
                return "FFZ Emotes";
            else if (setId.equals(ChatUtils.EmoteSet.BTTV.getId()))
                return "BetterTTV Emotes";
        }

        return org;
    }

    /**
     * Class: MessageRecyclerItem
     * signature: public void g()
     */
    public static boolean hookMsgRemover(boolean org) {
        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        if (!prefManager.isPreventMsg())
            return org;

        return true;
    }

    /**
     * Class: CommunityPointsButtonViewDelegate
     * signature: private final void e(CommunityPointsModel communityPointsModel)
     */
    public static void setClicker(final View pointButtonView, CommunityPointsModel pointsModel) {
        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        if (!prefManager.isClickerOn())
            return;

        Helper helper = LoaderLS.getInstance().getHelper();
        helper.setClicker(pointButtonView, pointsModel);
    }

    /**
     * Class: StandaloneMediaClock
     * signatute: private PlaybackParameters f = PlaybackParameters.e;
     */
    public static PlaybackParameters hookVodPlayerStandaloneMediaClockInit(PlaybackParameters org) {
        PrefManager prefManager = LoaderLS.getInstance().getPrefManager();
        float speed = prefManager.getExoplayerSpeed();
        if (speed == org.a)
            return org;

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element == null)
                continue;

            String clazz = element.getClassName();
            if (TextUtils.isEmpty(clazz))
                continue;

            if (!clazz.equals("tv.twitch.a.k.v.j0.w"))
                continue;

            return new PlaybackParameters(speed);
        }

        return PlaybackParameters.e;
    }

    /**
     * Class: MessageRecyclerItem
     * signature:
     */
    public static Spanned addTimestampToMessage(Spanned message, String messageId) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isTimestampsOn())
            return message;

        if (TextUtils.isEmpty(message) || TextUtils.isEmpty(messageId))
            return message;

        return ChatUtils.addTimestamp(message, new Date());
    }

    /**
     * Class: ChatController
     * signature: ChatEmoticonSet[] b()
     */
    public static ChatEmoticonSet[] hookChatEmoticonSet(ChatEmoticonSet[] orgSet) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isHookEmoticonSetOn())
            return orgSet;

        if (orgSet == null)
            return null;

        Helper helper = LoaderLS.getInstance().getHelper();
        EmoteManager emoteManager = LoaderLS.getInstance().getEmoteManager();

        int currentChannel = helper.getCurrentChannel();
        Collection<Emote> globalEmotes = emoteManager.getGlobalEmotes();
        Collection<Emote> bttvEmotes = emoteManager.getBttvEmotes(currentChannel);
        Collection<Emote> ffzEmotes = emoteManager.getFfzEmotes(currentChannel);

        ChatEmoticonSet[] newSet = new ChatEmoticonSet[orgSet.length+3];
        System.arraycopy(orgSet, 0, newSet, 0, orgSet.length);

        ChatEmoticonSet bttvEmoticonSet = new ChatEmoticonSet();
        bttvEmoticonSet.emoticonSetId = ChatUtils.EmoteSet.BTTV.getId();
        bttvEmoticonSet.emoticons = ChatUtils.emotesToChatEmoticonArr(bttvEmotes);

        ChatEmoticonSet ffzEmoticonSet = new ChatEmoticonSet();
        ffzEmoticonSet.emoticonSetId = ChatUtils.EmoteSet.FFZ.getId();
        ffzEmoticonSet.emoticons = ChatUtils.emotesToChatEmoticonArr(ffzEmotes);

        ChatEmoticonSet globalEmoticonSet = new ChatEmoticonSet();
        globalEmoticonSet.emoticonSetId = ChatUtils.EmoteSet.GLOBAL.getId();
        globalEmoticonSet.emoticons = ChatUtils.emotesToChatEmoticonArr(globalEmotes);

        newSet[newSet.length-1] = bttvEmoticonSet;
        newSet[newSet.length-2] = ffzEmoticonSet;
        newSet[newSet.length-3] = globalEmoticonSet;

        return newSet;
    }

    /**
     * Class: *.*
     */
    public static boolean hookAd(boolean org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (manager.isAdblockOn())
            return false;

        return org;
    }

    /**
     * Class: ChatConnectionController
     * signature: private final void a(ChannelInfo channelInfo)
     */
    public static void requestEmotes(ChannelInfo channelInfo) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isEmotesOn())
            return;

        EmoteManager emoteManager = LoaderLS.getInstance().getEmoteManager();
        emoteManager.requestChannelEmoteSet(channelInfo.getId(), false);
    }

    /**
     * Class: ModelTheatreModeTracker
     * signature: public c(f1 f1Var, Playable playable, Object pageViewTracker)
     */
    public static void requestEmotes(final f1 playableModelParser, final Playable playable) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isEmotesOn())
            return;

        EmoteManager emoteManager = LoaderLS.getInstance().getEmoteManager();
        emoteManager.requestChannelEmoteSet(Helper.getChannelId(playableModelParser, playable), false);
    }

    /**
     * Class: FollowedGamesFetcher
     * signature: public final boolean j()
     */
    public static boolean hookFollowerFetcher(boolean org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isDisableFollowedGames())
            return org;

        return false;
    }

    /**
     * Class: RecommendedStreamsFetcher
     * signature: public final boolean j()
     */
    public static boolean hookRecommendedFetcher(boolean org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isDisableRecommendations())
            return org;

        return false;
    }

    /**
     * Class: ResumeWatchingVideosFetcher
     * signature: public final boolean j()
     */
    public static boolean hookResumeWatchingFetcher(boolean org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isDisableRecentWatching())
            return org;

        return false;
    }

    /**
     * Class: VideoDebugConfig
     * signature: public final boolean a()
     */
    public static boolean hookVideoDebugPanel(boolean org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isShowVideoDebugPanel())
            return org;

        return true;
    }

    /**
     * Class: MiniPlayerSize
     * signature: public final int b()
     */
    public static int hookMiniplayerSize(int size) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        float k = manager.getMiniplayerSize();
        if (k == 1.0f)
            return size;

        return (int) (k * size);
    }

    /**
     * Class: PlayerImplementation
     * signature: public final PlayerImplementation getProviderForName(String str)
     */
    public static String hookPlayerProvider(String org) {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isExoPlayerOn())
            return org;

        if (TextUtils.isEmpty(org))
            return org;

        return EXOPLAYER_CONSTANT;
    }

    /**
     * Class: SearchSuggestionAdapterBinder
     * signature: public final void a(Object obj)
     */
    public static boolean isJumpDisRecentSearch() {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isDisableRecentSearch())
            return false;

        return true;
    }

    /**
     * Class: *.*
     */
    public static boolean isAdBlockJump() {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (manager.isAdblockOn())
            return true;

        return false;
    }

    /**
     * Class: RecommendationAutoPlayPresenter
     * signature: public final void prepareRecommendationForCurrentModel(T t)
     */
    public static boolean isJumpDisableAutoplay() {
        PrefManager manager = LoaderLS.getInstance().getPrefManager();
        if (!manager.isDisableAutoplay())
            return false;

        return true;
    }

    /**
     * For injecting emotes in emote menu we need known current channel id
     * Class: ChatMessageInputViewPresenter
     * signature: public final void a(b bVar)
     */
    public static void setCurrentChannel(b event) {
        if (event == null) {
            Logger.error("event is null");
            return;
        }

        Helper helper = LoaderLS.getInstance().getHelper();
        helper.setCurrentChannel(event.a().getId());
    }

    /**
     * Some hooks
     */
    public static void helper() {
        if (!isAdBlockJump()) {
        } // TODO: __HOOK
        boolean ad = hookAd(true); // TODO: __HOOK
        Object o = hookUrlDrawableConstructor(new h(null, null)); // TODO: __HOOK
        o = hookVodPlayerStandaloneMediaClockInit(new PlaybackParameters(0.0f)); // TODO: __HOOK
    }
}