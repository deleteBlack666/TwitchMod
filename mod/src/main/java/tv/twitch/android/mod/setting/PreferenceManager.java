package tv.twitch.android.mod.setting;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.models.Preferences;
import tv.twitch.android.mod.models.preferences.ImageSize;
import tv.twitch.android.mod.models.preferences.Gifs;
import tv.twitch.android.mod.models.preferences.MsgDelete;
import tv.twitch.android.mod.models.preferences.PlayerImpl;
import tv.twitch.android.mod.models.preferences.SureStreamAdBlock;
import tv.twitch.android.mod.models.preferences.UserMessagesFiltering;
import tv.twitch.android.mod.util.ChatMesssageFilteringUtil;
import tv.twitch.android.mod.util.Helper;
import tv.twitch.android.mod.util.Logger;


public class PreferenceManager implements PreferenceWrapper.PreferenceListener {
    public static final String TWITCH_DARK_THEME_KEY = "dark_theme_enabled";
    public static final String MOD_BANNER_KEY = "mod_show_banner";

    public static final PreferenceManager INSTANCE = new PreferenceManager();

    private boolean showBttvEmoteInChat;
    private boolean isChatTimestampsEnabled;
    private boolean isPlayerAdblockOn;
    private boolean isVolumeSwiperEnabled;
    private boolean isBrightnessSwiperEnabled;
    private boolean hideFollowRecommendation;
    private boolean hideFollowResume;
    private boolean hideFollowGame;
    private boolean hideDiscoverTab;
    private boolean hideEsportsTab;
    private boolean hideRecentSearch;
    private boolean isDevModeOn;
    private boolean disableTheatreAutoplay;
    private boolean hideSystemMessagesInChat;
    private boolean isInterceptorEnabled;
    private boolean showChatForBannedUser;
    private boolean showMentionHighlightsInChat;
    private boolean disableClipfinity;
    private boolean useRobottyService;
    private boolean showFloatingChat;
    private boolean isCompactPlayerFollowViewEnabled;
    private boolean showPlayerStatButton;
    private boolean showPlayerRefreshButton;
    private boolean hideChatRestriction;
    private boolean showWideEmotes;
    private boolean lockSwiper;
    private boolean disableGoogleBilling;
    private boolean showSwipperLockButton;
    private boolean useAutoclicker;
    private boolean showHypeTrain;
    private boolean shouldHideChatHeaderContainer;
    private boolean showStreamUptime;
    private boolean ffzBadges;
    private boolean hideBitsButton;

    private boolean shouldShowBanner;
    private boolean isBannerShown;

    private String userFilterText;
    private String userAgent;

    private @ImageSize
    String imageSize;
    private int landscapeChatScale;
    private int landscapeChatScaleMax;
    private @Gifs String gifsRenderType;
    private @MsgDelete String msgDeleteStrategy;
    private int miniPlayerScale;
    private @PlayerImpl String playerImplemetation;
    private @SureStreamAdBlock String sureStreamAdBlockVariant;
    private @UserMessagesFiltering String filterChatMessageByLevel;
    private int floatingChatSize;
    private int robottyLimit;
    private int playerForwardSeek;
    private int playerBackwardSeek;
    private int exoplayerSpeed;
    private int chatMessageFontSize;

    private int lastBuildNum;

    private boolean isGifsAnimated;

    private PreferenceWrapper mWrapper;

    private boolean isDarkThemeEnabled;

    private PreferenceManager() {}

    public void initialize(Context context) {
        if (mWrapper != null)
            throw new ExceptionInInitializerError("mWrapper is not null");

        mWrapper = new PreferenceWrapper(context);
        initializePreferences();
    }

    private void initializePreferences() {
        setupPreferences();
        mWrapper.registerPreferenceListener(this);
    }

    private void setupPreferences() {
        showBttvEmoteInChat = getBoolean(Preferences.BTTV_EMOTES);
        isChatTimestampsEnabled = getBoolean(Preferences.CHAT_TIMESTAMP);
        isPlayerAdblockOn = getBoolean(Preferences.PLAYER_ADBLOCK);
        isVolumeSwiperEnabled = getBoolean(Preferences.VOLUME_GESTURE);
        isBrightnessSwiperEnabled = getBoolean(Preferences.BRIGHTNESS_GESTURE);
        isVolumeSwiperEnabled = getBoolean(Preferences.VOLUME_GESTURE);
        hideFollowRecommendation = getBoolean(Preferences.HIDE_FOLLOW_RECOMMENDATIONS);
        hideFollowResume = getBoolean(Preferences.HIDE_FOLLOW_RESUME);
        hideFollowGame = getBoolean(Preferences.HIDE_FOLLOW_GAMES);
        hideDiscoverTab = getBoolean(Preferences.HIDE_DISCOVER_TAB);
        hideEsportsTab = getBoolean(Preferences.HIDE_ESPORTS_TAB);
        hideRecentSearch = getBoolean(Preferences.HIDE_RECENT_SEARCH_RESULTS);
        disableTheatreAutoplay = getBoolean(Preferences.DISABLE_THEATRE_AUTOPLAY);
        hideSystemMessagesInChat = getBoolean(Preferences.FILTER_CHAT_SYSTEM);
        isInterceptorEnabled = getBoolean(Preferences.DEV_INTERCEPTOR);
        showChatForBannedUser = getBoolean(Preferences.SHOW_CHAT_FOR_BANNED_USER);
        showMentionHighlightsInChat = getBoolean(Preferences.CHAT_MENTION_HIGHLIGHT);
        disableClipfinity = getBoolean(Preferences.DISABLE_CLIPFINITY);
        isDevModeOn = getBoolean(Preferences.DEV_MODE);
        useRobottyService = getBoolean(Preferences.MESSAGE_HISTORY);
        showFloatingChat = getBoolean(Preferences.PLAYER_FLOATING_CHAT);
        isCompactPlayerFollowViewEnabled = getBoolean(Preferences.COMPACT_PLAYER_FOLLOW_VIEW);
        showPlayerStatButton = getBoolean(Preferences.PLAYER_STAT_BUTTON);
        showPlayerRefreshButton = getBoolean(Preferences.PLAYER_REFRESH_BUTTON);
        hideChatRestriction = getBoolean(Preferences.HIDE_CHAT_RESTRICTION);
        showWideEmotes = getBoolean(Preferences.SHOW_WIDE_EMOTES);
        disableGoogleBilling = getBoolean(Preferences.DISABLE_GOOGLE_BILLING);
        showSwipperLockButton = getBoolean(Preferences.GESTURES_LOCK_BUTTON);
        useAutoclicker = getBoolean(Preferences.AUTOCLICKER);
        showHypeTrain = getBoolean(Preferences.SHOW_HYPE_TRAIN);
        shouldHideChatHeaderContainer = getBoolean(Preferences.HIDE_CHAT_HEADER);
        showStreamUptime = getBoolean(Preferences.STREAM_UPTIME);
        ffzBadges = getBoolean(Preferences.FFZ_BADGES);
        hideBitsButton = getBoolean(Preferences.HIDE_BITS_BUTTON);

        lastBuildNum = getInt(Preferences.LAST_BUILD_NUMBER);

        userFilterText = getString(Preferences.USER_FILTER_TEXT);

        imageSize = getImageSizePref();
        landscapeChatScale = getInt(Preferences.LANDSCAPE_CHAT_SCALE);
        landscapeChatScaleMax = getInt(Preferences.LANDSCAPE_CHAT_SCALE_MAX);
        gifsRenderType = getString(Preferences.GIFS_RENDER_TYPE);
        isGifsAnimated = gifsRenderType.equals(Gifs.ANIMATED);
        msgDeleteStrategy = getString(Preferences.MSG_DELETE_STRATEGY);
        miniPlayerScale = getInt(Preferences.MINIPLAYER_SCALE);
        playerImplemetation = getString(Preferences.PLAYER_IMPLEMENTATION);
        sureStreamAdBlockVariant = getString(Preferences.SURESTREAM_ADBLOCK);
        filterChatMessageByLevel = getString(Preferences.CHAT_MESSAGE_FILTER_LEVEL);
        floatingChatSize = getInt(Preferences.PLAYER_FLOATING_CHAT_SIZE);
        robottyLimit = getInt(Preferences.MESSAGE_HISTORY_LIMIT);
        playerForwardSeek = getInt(Preferences.PLAYER_FORWARD_SEEK);
        playerBackwardSeek = getInt(Preferences.PLAYER_BACKWARD_SEEK);
        chatMessageFontSize = getInt(Preferences.CHAT_MESSAGE_FONT_SIZE);
        exoplayerSpeed = getInt(Preferences.EXOPLAYER_SPEED);

        isDarkThemeEnabled = getBoolean(TWITCH_DARK_THEME_KEY, false);
        shouldShowBanner = getBoolean(MOD_BANNER_KEY, true);

        lockSwiper = false;
    }

    private @ImageSize String getImageSizePref() {
        String defVal = Helper.isHiDensityDevice() ? ImageSize.LARGE : ImageSize.MEDIUM;
        return mWrapper.getString(Preferences.EMOTE_SIZE.getKey(), defVal);
    }

    public void updateBoolean(String key, boolean val) {
        if (mWrapper == null) {
            Logger.error("mWrapper is null");
            return;
        }

        mWrapper.updateBoolean(key, val);
    }

    public void updateString(String key, String val) {
        if (mWrapper == null) {
            Logger.error("mWrapper is null");
            return;
        }

        mWrapper.updateString(key, val);
    }

    public void updateInt(String key, int val) {
        if (mWrapper == null) {
            Logger.error("mWrapper is null");
            return;
        }

        mWrapper.updateInt(key, val);
    }

    private int getInt(Preferences preference) {
        if (preference == null) {
            throw new IllegalArgumentException("preference is null");
        }

        return mWrapper.getInt(preference.getKey(), getIntegerFromDefaultValue(preference.getDefaultValue()));
    }

    private int getIntegerFromDefaultValue(Preferences.DefaultValue df) {
        if (df == null) {
            Logger.error("df is null");
            return 0;
        }

        Object val = df.getValue();
        if (val == null) {
            Logger.error("val is null");
            return 0;
        }

        if (val instanceof Integer) {
            return (Integer) val;
        } else if (val instanceof String) {
            return Integer.parseInt((String) val);
        } else {
            return Integer.parseInt(val.toString());
        }
    }
    private boolean getBoolean(Preferences preference) {
        if (preference == null) {
            throw new IllegalArgumentException("preference is null");
        }

        return mWrapper.getBoolean(preference.getKey(), getBooleanFromDefaultValue(preference.getDefaultValue()));
    }

    private boolean getBooleanFromDefaultValue(Preferences.DefaultValue df) {
        if (df == null) {
            Logger.error("df is null");
            return false;
        }

        Object val = df.getValue();
        if (val == null) {
            Logger.error("val is null");
            return false;
        }

        if (val instanceof Boolean)
            return (Boolean) val;
        if (val instanceof String) {
            return Boolean.parseBoolean((String) val);
        } else {
            return Boolean.parseBoolean(val.toString());
        }
    }

    private String getStringFromDefaultValue(Preferences.DefaultValue df) {
        if (df == null) {
            Logger.error("df is null");
            return null;
        }

        Object val = df.getValue();
        if (val == null) {
            Logger.error("val is null");
            return null;
        }

        if (val instanceof String) {
            return (String) val;
        } else {
            return val.toString();
        }
    }

    private String getString(Preferences preference) {
        if (preference == null) {
            throw new IllegalArgumentException("preference is null");
        }

        return mWrapper.getString(preference.getKey(), getStringFromDefaultValue(preference.getDefaultValue()));
    }

    private boolean getBoolean(String key, boolean def) {
        if (mWrapper == null) {
            Logger.error("wrapper is null");
            return def;
        }

        return mWrapper.getBoolean(key, def);
    }


    public boolean showBttvEmotesInChat() {
        return showBttvEmoteInChat;
    }

    public boolean isHighlightingEnabled() {
        return showMentionHighlightsInChat;
    }

    public boolean disableClipfinity() {
        return disableClipfinity;
    }

    public boolean showChatForBannedUser() {
        return showChatForBannedUser;
    }

    public boolean isChatTimestampsEnabled() {
        return isChatTimestampsEnabled;
    }

    public boolean isChatTimestampsVodEnabled() {
        return isChatTimestampsEnabled();
    }

    public boolean disableTheatreAutoplay() {
        return disableTheatreAutoplay;
    }
    
    public boolean hideRecentSearchResult() {
        return hideRecentSearch;
    }

    public boolean hideFollowResumeSection() {
        return hideFollowResume;
    }

    public boolean hideFollowRecommendationSection() {
        return hideFollowRecommendation;
    }

    public boolean hideFollowGameSection() {
        return hideFollowGame;
    }

    public boolean isVolumeSwiperEnabled() {
        return isVolumeSwiperEnabled;
    }

    public boolean isBrightnessSwiperEnabled() {
        return isBrightnessSwiperEnabled;
    }

    public boolean isDevModeOn() {
        return isDevModeOn;
    }

    public boolean hideSystemMessagesInChat() {
        return hideSystemMessagesInChat;
    }

    public boolean isInterceptorEnabled() {
        return isInterceptorEnabled;
    }

    public boolean hideDiscoverTab() {
        return hideDiscoverTab;
    }

    public boolean hideEsportsTab() {
        return hideEsportsTab;
    }

    public boolean showFloatingChat() {
        return showFloatingChat;
    }

    public boolean isDarkThemeEnabled() {
        return isDarkThemeEnabled;
    }

    public boolean isPlayerAdblockOn() {
        return isPlayerAdblockOn;
    }

    public boolean showMessageHistory() {
        return useRobottyService;
    }

    public boolean isCompactPlayerFollowViewEnabled() {
        return isCompactPlayerFollowViewEnabled;
    }

    public boolean shouldShowPlayerRefreshButton() {
        return showPlayerRefreshButton;
    }

    public boolean shouldShowPlayerStatButton() {
        return showPlayerStatButton;
    }

    public boolean isBannerShown() {
        return isBannerShown;
    }

    public void setBannerShown(boolean z) {
        isBannerShown = z;
    }

    public boolean shouldShowBanner() {
        return shouldShowBanner;
    }

    public void setShouldShowBanner(boolean z) {
        updateBoolean(MOD_BANNER_KEY, z);
    }

    public boolean hideChatRestriction() {
        return hideChatRestriction;
    }

    public boolean fixWideEmotes() {
        return showWideEmotes;
    }

    public boolean isGoogleBillingDisabled() {
        return disableGoogleBilling;
    }

    public boolean isAutoclickerEnabled() {
        return useAutoclicker;
    }

    public boolean shouldShowHypeTrain() {
        return showHypeTrain;
    }

    public boolean shouldHideChatHeaderContainer() {
        return shouldHideChatHeaderContainer;
    }

    public boolean isSurestreamAdblockV1On() {
        return getSureStreamAdBlockVariant().equals(SureStreamAdBlock.V1);
    }

    public boolean isSurestreamAdblockV3On() {
        return getSureStreamAdBlockVariant().equals(SureStreamAdBlock.V3);
    }

    public boolean shouldShowStreamUptime() {
        return showStreamUptime;
    }

    public @ImageSize
    String getImageSize() {
        return imageSize;
    }

    public @PlayerImpl String getPlayerImplementation() {
        return playerImplemetation;
    }

    public @SureStreamAdBlock String getSureStreamAdBlockVariant() {
        return sureStreamAdBlockVariant;
    }

    public @UserMessagesFiltering String getFilterMessageLevel() {
        return filterChatMessageByLevel;
    }

    public int getLandscapeChatScale() {
        return landscapeChatScale;
    }

    public int getLandscapeChatScaleMax() {
        return landscapeChatScaleMax;
    }

    public float getMiniPlayerScale() {
        return miniPlayerScale / 100f;
    }

    public int getFloatingChatQueueSize() {
        return floatingChatSize;
    }

    public @MsgDelete String getMsgDelete() {
        return msgDeleteStrategy;
    }

    public @Gifs String getGifsStrategy() {
        return gifsRenderType;
    }

    public boolean isGifsAnimated() {
        return isGifsAnimated;
    }

    public int getMessageHistoryLimit() {
        return robottyLimit;
    }

    public int getPlayerForwardSeek() {
        return playerForwardSeek;
    }

    public float getExoplayerSpeed() {
        return exoplayerSpeed / 100F;
    }

    public int getChatMessageFontSize() {
        return chatMessageFontSize;
    }

    public int getPlayerBackwardSeek() {
        return playerBackwardSeek;
    }

    public String getUserFilterText() {
        return userFilterText;
    }

    public boolean isUserFilterTextEmpty() {
        return getUserFilterText().isEmpty();
    }

    public boolean shouldLockSwiper() {
        return lockSwiper;
    }

    public void setLockSwiper(boolean z) {
        lockSwiper = z;
    }

    public boolean shouldShowLockButton() {
        return showSwipperLockButton;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getLastBuildNumber() {
        return lastBuildNum;
    }

    public boolean isFfzBadgesEnabled() {
        return ffzBadges;
    }

    public boolean hideBitsButton() {
        return hideBitsButton;
    }

    public void updateBuildNumber() {
        updateInt(Preferences.LAST_BUILD_NUMBER.getKey(), LoaderLS.getBuildNumber());
    }

    @Override
    public void onPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case TWITCH_DARK_THEME_KEY:
                isDarkThemeEnabled = getBoolean(TWITCH_DARK_THEME_KEY, false);
                return;
            case MOD_BANNER_KEY:
                shouldShowBanner = sharedPreferences.getBoolean(MOD_BANNER_KEY, false);
                return;
        }

        Preferences preference = Preferences.lookupByKey(key);
        if (preference == null) {
            Logger.warning("Preference not found. Key: " + key);
            return;
        }

        switch (preference) {
            case GIFS_RENDER_TYPE:
                gifsRenderType = sharedPreferences.getString(key, gifsRenderType);
                if (gifsRenderType != null) {
                    isGifsAnimated = gifsRenderType.equals(Gifs.ANIMATED);
                } else {
                    isGifsAnimated = false;
                }
                break;
            case PLAYER_ADBLOCK:
                isPlayerAdblockOn = sharedPreferences.getBoolean(key, isPlayerAdblockOn);
                break;
            case DEV_MODE:
                isDevModeOn = sharedPreferences.getBoolean(key, isDevModeOn);
                break;
            case EMOTE_SIZE:
                imageSize = sharedPreferences.getString(key, imageSize);
                break;
            case BTTV_EMOTES:
                showBttvEmoteInChat = sharedPreferences.getBoolean(key, showBttvEmoteInChat);
                break;
            case USER_FILTER_TEXT:
                userFilterText = sharedPreferences.getString(key, userFilterText);
                ChatMesssageFilteringUtil.INSTANCE.updateBlocklist(userFilterText);
                break;
            case PLAYER_IMPLEMENTATION:
                playerImplemetation = sharedPreferences.getString(key, playerImplemetation);
                break;
            case SURESTREAM_ADBLOCK:
                sureStreamAdBlockVariant = sharedPreferences.getString(key, sureStreamAdBlockVariant);
                break;
            case PLAYER_STAT_BUTTON:
                showPlayerStatButton = sharedPreferences.getBoolean(key, showPlayerStatButton);
                break;
            case PLAYER_FLOATING_CHAT:
                showFloatingChat = sharedPreferences.getBoolean(key, showFloatingChat);
                break;
            case MESSAGE_HISTORY_LIMIT:
                robottyLimit = sharedPreferences.getInt(key, robottyLimit);
                break;
            case VOLUME_GESTURE:
                isVolumeSwiperEnabled = sharedPreferences.getBoolean(key, isVolumeSwiperEnabled);
                break;
            case PLAYER_REFRESH_BUTTON:
                showPlayerRefreshButton = sharedPreferences.getBoolean(key, showPlayerRefreshButton);
                break;
            case SHOW_CHAT_FOR_BANNED_USER:
                showChatForBannedUser = sharedPreferences.getBoolean(key, showChatForBannedUser);
                break;
            case CHAT_TIMESTAMP:
                isChatTimestampsEnabled = sharedPreferences.getBoolean(key, isChatTimestampsEnabled);
                break;
            case PLAYER_FLOATING_CHAT_SIZE:
                floatingChatSize = sharedPreferences.getInt(key, floatingChatSize);
                break;
            case MESSAGE_HISTORY:
                useRobottyService = sharedPreferences.getBoolean(key, useRobottyService);
                break;
            case CHAT_MENTION_HIGHLIGHT:
                showMentionHighlightsInChat = sharedPreferences.getBoolean(key, showMentionHighlightsInChat);
                break;
            case LANDSCAPE_CHAT_SCALE:
                landscapeChatScale = sharedPreferences.getInt(key, landscapeChatScale);
                break;
            case LANDSCAPE_CHAT_SCALE_MAX:
                landscapeChatScaleMax = sharedPreferences.getInt(key, landscapeChatScaleMax);
                break;
            case HIDE_ESPORTS_TAB:
                hideEsportsTab = sharedPreferences.getBoolean(key, hideEsportsTab);
                break;
            case MINIPLAYER_SCALE:
                miniPlayerScale = sharedPreferences.getInt(key, miniPlayerScale);
                break;
            case BRIGHTNESS_GESTURE:
                isBrightnessSwiperEnabled = sharedPreferences.getBoolean(key, isBrightnessSwiperEnabled);
                break;
            case HIDE_DISCOVER_TAB:
                hideDiscoverTab = sharedPreferences.getBoolean(key, hideDiscoverTab);
                break;
            case HIDE_FOLLOW_GAMES:
                hideFollowGame = sharedPreferences.getBoolean(key, hideFollowGame);
                break;
            case COMPACT_PLAYER_FOLLOW_VIEW:
                isCompactPlayerFollowViewEnabled = sharedPreferences.getBoolean(key, isCompactPlayerFollowViewEnabled);
                break;
            case MSG_DELETE_STRATEGY:
                msgDeleteStrategy = sharedPreferences.getString(key, msgDeleteStrategy);
                break;
            case SHOW_WIDE_EMOTES:
                showWideEmotes = sharedPreferences.getBoolean(key, showWideEmotes);
                break;
            case HIDE_CHAT_RESTRICTION:
                hideChatRestriction = sharedPreferences.getBoolean(key, hideChatRestriction);
                break;
            case DEV_INTERCEPTOR:
                isInterceptorEnabled = sharedPreferences.getBoolean(key, isInterceptorEnabled);
                break;
            case DISABLE_CLIPFINITY:
                disableClipfinity = sharedPreferences.getBoolean(key, disableClipfinity);
                break;
            case DISABLE_THEATRE_AUTOPLAY:
                disableTheatreAutoplay = sharedPreferences.getBoolean(key, disableTheatreAutoplay);
                break;
            case CHAT_MESSAGE_FILTER_LEVEL:
                filterChatMessageByLevel = sharedPreferences.getString(key, filterChatMessageByLevel);
                break;
            case FILTER_CHAT_SYSTEM:
                hideSystemMessagesInChat = sharedPreferences.getBoolean(key, hideSystemMessagesInChat);
                break;
            case HIDE_FOLLOW_RESUME:
                hideFollowResume = sharedPreferences.getBoolean(key, hideFollowResume);
                break;
            case HIDE_RECENT_SEARCH_RESULTS:
                hideRecentSearch = sharedPreferences.getBoolean(key, hideRecentSearch);
                break;
            case HIDE_FOLLOW_RECOMMENDATIONS:
                hideFollowRecommendation = sharedPreferences.getBoolean(key, hideFollowRecommendation);
                break;
            case DISABLE_GOOGLE_BILLING:
                disableGoogleBilling = sharedPreferences.getBoolean(key, disableGoogleBilling);
                break;
            case GESTURES_LOCK_BUTTON:
                showSwipperLockButton = sharedPreferences.getBoolean(key, showSwipperLockButton);
                break;
            case PLAYER_FORWARD_SEEK:
                playerForwardSeek = sharedPreferences.getInt(key, playerForwardSeek);
                break;
            case PLAYER_BACKWARD_SEEK:
                playerBackwardSeek = sharedPreferences.getInt(key, playerBackwardSeek);
                break;
            case EXOPLAYER_SPEED:
                exoplayerSpeed = sharedPreferences.getInt(key, exoplayerSpeed);
                break;
            case CHAT_MESSAGE_FONT_SIZE:
                chatMessageFontSize = sharedPreferences.getInt(key, chatMessageFontSize);
                break;
            case AUTOCLICKER:
                useAutoclicker = sharedPreferences.getBoolean(key, useAutoclicker);
                break;
            case SHOW_HYPE_TRAIN:
                showHypeTrain = sharedPreferences.getBoolean(key, showHypeTrain);
                break;
            case HIDE_CHAT_HEADER:
                shouldHideChatHeaderContainer = sharedPreferences.getBoolean(key, shouldHideChatHeaderContainer);
                break;
            case STREAM_UPTIME:
                showStreamUptime = sharedPreferences.getBoolean(key, showStreamUptime);
                break;
            case LAST_BUILD_NUMBER:
                lastBuildNum = sharedPreferences.getInt(key, lastBuildNum);
                break;
            case FFZ_BADGES:
                ffzBadges = sharedPreferences.getBoolean(key, ffzBadges);
                break;
            case HIDE_BITS_BUTTON:
                hideBitsButton = sharedPreferences.getBoolean(key, hideBitsButton);
                break;
            default:
                Logger.warning("Check key: " + key);
                break;
        }
    }
}
