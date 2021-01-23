package tv.twitch.android.mod.preference;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;


import java.util.logging.Logger;

import tv.twitch.android.mod.libs.binaryprefs.BinaryPreferencesBuilder;
import tv.twitch.android.mod.libs.binaryprefs.Preferences;
import tv.twitch.android.mod.models.Preference;
import tv.twitch.android.mod.models.preferences.ImageSize;
import tv.twitch.android.mod.models.preferences.Gifs;
import tv.twitch.android.mod.models.preferences.MsgDelete;
import tv.twitch.android.mod.models.preferences.PlayerImpl;
import tv.twitch.android.mod.models.preferences.SureStreamAdBlock;
import tv.twitch.android.mod.models.preferences.UserMessagesFiltering;
import tv.twitch.android.mod.preference.adapter.PreferenceDataStoreBinaryAdapter;


public class PreferenceManager implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String TWITCH_DARK_THEME_KEY = "dark_theme_enabled";

    public static final PreferenceManager INSTANCE = new PreferenceManager();

    private SharedPreferences mDefaultSharedPreferences;
    private Preferences binaryPreferences;

    private boolean lockGestures;
    private boolean shouldAnimateGifs;

    private boolean isDarkThemeEnabled;
    private boolean isBannerShown;

    private PreferenceManager() {}

    public void initialize(Context context) {
        if (binaryPreferences != null) {
            throw new ExceptionInInitializerError("binaryPreferences is not null");
        }

        if (mDefaultSharedPreferences != null) {
            throw new ExceptionInInitializerError("mDefaultSharedPreferences is not null");
        }

        binaryPreferences = new BinaryPreferencesBuilder(context)
                .name("twitchmod")
                .exceptionHandler(Throwable::printStackTrace)
                .build();

        mDefaultSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

        setupPreferences();
    }

    private void setupPreferences() {
        isDarkThemeEnabled = mDefaultSharedPreferences.getBoolean(TWITCH_DARK_THEME_KEY, false);

        shouldAnimateGifs = false;
        lockGestures = false;

        mDefaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        binaryPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private int getInt(Preference.ValueHolder valueHolder) {
        if (valueHolder == null) {
            return 0;
        }

        if (valueHolder instanceof Preference.IntegerValue) {
            Preference.IntegerValue integerValue = (Preference.IntegerValue) valueHolder;
            return integerValue.getValue();
        }

        Object val = valueHolder.getValue();
        if (val == null) {
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

    private boolean getBoolean(Preference.ValueHolder valueHolder) {
        if (valueHolder == null) {
            return false;
        }

        if (valueHolder instanceof Preference.BooleanValue) {
            Preference.BooleanValue booleanValue = (Preference.BooleanValue) valueHolder;
            return booleanValue.getValue();
        }

        Object val = valueHolder.getValue();
        if (val == null) {
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

    private String getString(Preference.ValueHolder valueHolder) {
        if (valueHolder == null) {
            return null;
        }

        if (valueHolder instanceof Preference.StringValue) {
            Preference.StringValue stringValue = (Preference.StringValue) valueHolder;
            return stringValue.getValue();
        }

        Object val = valueHolder.getValue();
        if (val == null) {
            return null;
        }

        if (val instanceof String) {
            return (String) val;
        } else {
            return val.toString();
        }
    }

    private boolean readBoolean(Preference preference) {
        return binaryPreferences.getBoolean(preference.getKey(), getBoolean(preference.getDefaultValue()));
    }

    private int readInt(Preference preference) {
        return binaryPreferences.getInt(preference.getKey(), getInt(preference.getDefaultValue()));
    }

    private String readString(Preference preference) {
        return binaryPreferences.getString(preference.getKey(), getString((preference.getDefaultValue())));
    }

    public void writeBoolean(String key, String value) {
        binaryPreferences.edit().putString(key, value).apply();
    }

    public void writeInt(String key, int value) {
        binaryPreferences.edit().putInt(key, value).apply();
    }

    public void writeBoolean(String key, boolean value) {
        binaryPreferences.edit().putBoolean(key, value).apply();
    }

    // --------------------------------------------BOOL---------------------------------------------

    public boolean showBttvEmotesInChat() {
        return readBoolean(Preference.BTTV_EMOTES);
    }

    public boolean isHighlightingEnabled() {
        return readBoolean(Preference.CHAT_MENTION_HIGHLIGHT);
    }

    public boolean disableClipfinity() {
        return readBoolean(Preference.DISABLE_CLIPFINITY);
    }

    public boolean showChatForBannedUser() {
        return readBoolean(Preference.SHOW_CHAT_FOR_BANNED_USER);
    }

    public boolean isChatTimestampsEnabled() {
        return readBoolean(Preference.CHAT_TIMESTAMP);
    }

    public boolean isChatTimestampsVodEnabled() {
        return readBoolean(Preference.CHAT_TIMESTAMP);
    }

    public boolean disableTheatreAutoplay() {
        return readBoolean(Preference.DISABLE_THEATRE_AUTOPLAY);
    }
    
    public boolean hideRecentSearchResult() {
        return readBoolean(Preference.HIDE_RECENT_SEARCH_RESULTS);
    }

    public boolean hideFollowResumeSection() {
        return readBoolean(Preference.HIDE_FOLLOW_RESUME);
    }

    public boolean hideFollowRecommendationSection() {
        return readBoolean(Preference.HIDE_FOLLOW_RECOMMENDATIONS);
    }

    public boolean hideFollowGameSection() {
        return readBoolean(Preference.HIDE_FOLLOW_GAMES);
    }

    public boolean isVolumeSwiperEnabled() {
        return readBoolean(Preference.VOLUME_GESTURE);
    }

    public boolean isBrightnessSwiperEnabled() {
        return readBoolean(Preference.BRIGHTNESS_GESTURE);
    }

    public boolean isDevModeOn() {
        return readBoolean(Preference.DEV_MODE);
    }

    public boolean hideSystemMessagesInChat() {
        return readBoolean(Preference.FILTER_CHAT_SYSTEM);
    }

    public boolean isInterceptorEnabled() {
        return readBoolean(Preference.DEV_INTERCEPTOR);
    }

    public boolean hideDiscoverTab() {
        return readBoolean(Preference.HIDE_DISCOVER_TAB);
    }

    public boolean hideEsportsTab() {
        return readBoolean(Preference.HIDE_ESPORTS_TAB);
    }

    public boolean showFloatingChat() {
        return readBoolean(Preference.PLAYER_FLOATING_CHAT);
    }

    public boolean isDarkThemeEnabled() {
        return isDarkThemeEnabled;
    }

    public boolean isPlayerAdblockOn() {
        return readBoolean(Preference.PLAYER_ADBLOCK);
    }

    public boolean showMessageHistory() {
        return readBoolean(Preference.MESSAGE_HISTORY);
    }

    public boolean isCompactPlayerFollowViewEnabled() {
        return readBoolean(Preference.COMPACT_PLAYER_FOLLOW_VIEW);
    }

    public boolean shouldShowPlayerRefreshButton() {
        return readBoolean(Preference.PLAYER_REFRESH_BUTTON);
    }

    public boolean shouldShowPlayerStatButton() {
        return readBoolean(Preference.PLAYER_STAT_BUTTON);
    }

    public boolean isBannerShown() {
        return isBannerShown;
    }

    public boolean hideChatRestriction() {
        return readBoolean(Preference.HIDE_CHAT_RESTRICTION);
    }

    public boolean fixWideEmotes() {
        return readBoolean(Preference.SHOW_WIDE_EMOTES);
    }

    public boolean isGoogleBillingDisabled() {
        return readBoolean(Preference.DISABLE_GOOGLE_BILLING);
    }

    public boolean isAutoclickerEnabled() {
        return readBoolean(Preference.AUTOCLICKER);
    }

    public boolean shouldShowHypeTrain() {
        return readBoolean(Preference.SHOW_HYPE_TRAIN);
    }

    public boolean shouldHideChatHeaderContainer() {
        return readBoolean(Preference.HIDE_CHAT_HEADER);
    }

    public boolean isSurestreamAdblockV1On() {
        return getSureStreamAdBlockVariant().equals(SureStreamAdBlock.V1);
    }

    public boolean isSurestreamAdblockV3On() {
        return getSureStreamAdBlockVariant().equals(SureStreamAdBlock.V3);
    }

    public boolean shouldShowStreamUptime() {
        return readBoolean(Preference.STREAM_UPTIME);
    }

    public boolean shouldLockSwiper() {
        return lockGestures;
    }

    public boolean isFfzBadgesEnabled() {
        return readBoolean(Preference.FFZ_BADGES);
    }

    public boolean hideBitsButton() {
        return readBoolean(Preference.HIDE_BITS_BUTTON);
    }

    public boolean shouldShowLockButton() {
        return readBoolean(Preference.GESTURES_LOCK_BUTTON);
    }

    public boolean isGifsAnimated() {
        return shouldAnimateGifs;
    }

    // ---------------------------------------------INT---------------------------------------------

    public int getLandscapeChatScale() {
        return readInt(Preference.LANDSCAPE_CHAT_SCALE);
    }

    public int getLandscapeChatScaleMax() {
        return readInt(Preference.LANDSCAPE_CHAT_SCALE_MAX);
    }

    public float getMiniPlayerScale() {
        return readInt(Preference.MINIPLAYER_SCALE) / 100f;
    }

    public int getFloatingChatQueueSize() {
        return readInt(Preference.PLAYER_FLOATING_CHAT_SIZE);
    }

    public int getMessageHistoryLimit() {
        return readInt(Preference.MESSAGE_HISTORY_LIMIT);
    }

    public int getPlayerForwardSeek() {
        return readInt(Preference.PLAYER_FORWARD_SEEK);
    }

    public int getChatMessageFontSize() {
        return readInt(Preference.CHAT_MESSAGE_FONT_SIZE) ;
    }

    public int getPlayerBackwardSeek() {
        return readInt(Preference.PLAYER_BACKWARD_SEEK);
    }

    public int getLastBuildNumber() {
        return readInt(Preference.LAST_BUILD_NUMBER);
    }

    public int getExoplayerSpeed() {
        return readInt(Preference.EXOPLAYER_SPEED);
    }

    // --------------------------------------------STRING------------------------------------------

    public @ImageSize String getImageSize() {
        return readString(Preference.EMOTE_SIZE);
    }

    public @PlayerImpl String getPlayerImplementation() {
        return readString(Preference.PLAYER_IMPLEMENTATION);
    }

    public @SureStreamAdBlock String getSureStreamAdBlockVariant() {
        return readString(Preference.SURESTREAM_ADBLOCK);
    }

    public @UserMessagesFiltering String getFilterMessageLevel() {
        return readString(Preference.CHAT_MESSAGE_FILTER_LEVEL);
    }

    public @MsgDelete String getMsgDelete() {
        return readString(Preference.MSG_DELETE_STRATEGY);
    }

    public @Gifs String getGifsStrategy() {
        return readString(Preference.GIFS_RENDER_TYPE);
    }

    public String getUserFilterText() {
        return readString(Preference.USER_FILTER_TEXT);
    }

    // ---------------------------------------------SET---------------------------------------------

    public void setBannerShown(boolean z) {
        isBannerShown = z;
    }

    public void setLockGestures(boolean z) {
        lockGestures = z;
    }

    public void updateLastBuildNum(int currentBuildNum) {
        writeInt(Preference.LAST_BUILD_NUMBER.getKey(), currentBuildNum);
    }

    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
        if (TextUtils.isEmpty(key))
            return;

        if (TWITCH_DARK_THEME_KEY.equals(key)) {
            isDarkThemeEnabled = sharedPreferences.getBoolean(TWITCH_DARK_THEME_KEY, false);
        }

        if (Preference.GIFS_RENDER_TYPE.getKey().equals(key)) {
            shouldAnimateGifs = getGifsStrategy().equals(Gifs.ANIMATED);
        }
    }

    public PreferenceDataStoreBinaryAdapter getBinaryPreferencesAdapter() {
        return new PreferenceDataStoreBinaryAdapter(binaryPreferences);
    }
}
