package tv.twitch.android.mod.models;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Preferences {
    BTTV_EMOTES("mod_bttv_emotes", "mod_settings_bttv_emotes", "mod_settings_bttv_emotes_desc", Type.BOOLEAN),
    CHAT_TIMESTAMPS("mod_chat_message_timestamp", "mod_settings_message_timestamp", "mod_settings_message_timestamp_desc", Type.BOOLEAN),
    CHAT_TIMESTAMPS_VOD("mod_chat_message_timestamp_vod", "mod_settings_message_timestamp_vod", "mod_settings_message_timestamp_vod_desc", Type.BOOLEAN),
    PLAYER_ADBLOCK("mod_player_adblock", "mod_settings_adblock", "mod_settings_adblock_desc", Type.BOOLEAN),
    VOLUME_SWIPER("mod_swipe_volume", "mod_settings_swipe_volume", "mod_settings_swipe_volume_desc", Type.BOOLEAN),
    BRIGHTNESS_SWIPER("mod_swipe_brightness", "mod_settings_swipe_brightness", "mod_settings_swipe_brightness_desc", Type.BOOLEAN),
    HIDE_FOLLOW_RECOMMENDED_STREAMS("mod_hide_follow_recommended", "mod_settings_disable_recommendations", "mod_settings_disable_recommendations_desc", Type.BOOLEAN),
    HIDE_FOLLOW_RESUME_STREAMS("mod_hide_follow_resume", "mod_settings_disable_resume_watching", "mod_settings_disable_resume_watching_desc", Type.BOOLEAN),
    HIDE_FOLLOW_GAMES("mod_hide_follow_games", "mod_settings_disable_followed_games", "mod_settings_disable_followed_games_desc", Type.BOOLEAN),
    HIDE_DISCOVER_TAB("mod_hide_discover_tab", "mod_settings_hide_discover", "mod_settings_hide_discover_desc", Type.BOOLEAN),
    HIDE_ESPORTS_TAB("mod_hide_esports_tab", "mod_settings_hide_esports", "mod_settings_hide_esports_desc", Type.BOOLEAN),
    HIDE_RECENT_SEARCH_RESULTS("mod_hide_recent_search_results", "mod_settings_disable_recent_search", "mod_settings_disable_recent_search_desc", Type.BOOLEAN),
    DEV_MODE("mod_dev_mode", "mod_settings_dev_mode", "mod_settings_dev_mode_desc", Type.BOOLEAN),
    DISABLE_THEATRE_AUTOPLAY("mod_disable_theatre_autoplay", "mod_settings_disable_autoplay", "mod_settings_disable_autoplay_desc", Type.BOOLEAN),
    GIFS_RENDER_TYPE("mod_gifs_render_type", "mod_settings_gifs", "mod_settings_gifs_desc", Type.STRING),
    EMOTE_SIZE("mod_emote_size_type", "mod_settings_emote_size", "mod_settings_emote_size_desc", Type.INTEGER),
    MINIPLAYER_SCALE("mod_miniplayer_scale_value", "mod_settings_miniplayer_size", "mod_settings_miniplayer_size_desc", Type.INTEGER),
    PLAYER_IMPELEMTATION("mod_player_impl_type", "mod_settings_player_implementation", "mod_settings_player_implementation_desc", Type.STRING),
    CHAT_MESSAGE_FILTER_LEVEL("mod_chat_message_filter_level_type", "mod_settings_filtering_level", "mod_settings_filtering_level_desc", Type.STRING),
    CHAT_MESSAGE_FILTER_SYSTEM("mod_chat_message_filter_system", "mod_settings_filtering_ignore_system", "mod_settings_filtering_ignore_system_desc", Type.BOOLEAN),
    DEV_INTERCEPTOR("mod_dev_enable_interceptor_v2", "mod_settings_interceptor", "mod_settings_interceptor_desc", Type.BOOLEAN),
    SHOW_CHAT_FOR_BANNED_USER("mod_bypass_chat_ban", "mod_settings_bypass_chat_ban", "mod_settings_bypass_chat_ban_desc", Type.BOOLEAN),
    LANDSCAPE_CHAT_SCALE("mod_chat_width_scale_value", "mod_settings_landscape_chat_width", "mod_settings_landscape_chat_width_desc", Type.INTEGER),
    LANDSCAPE_CHAT_SCALE_MAX("mod_chat_width_scale_max_value", "mod_settings_landscape_chat_width_max", "mod_settings_landscape_chat_width_max_desc", Type.INTEGER),
    CHAT_MENTION_HIGHLIGHTS("mod_chat_highlights", "mod_settings_chat_red_mention", "mod_settings_chat_red_mention_desc", Type.BOOLEAN),
    DISABLE_CLIPFINITY("mod_disable_new_clips_view", "mod_settings_disable_new_clips", "mod_settings_disable_new_clips_desc", Type.BOOLEAN),
    FLOAT_CHAT_SIZE("mod_floating_chat_size_value", "mod_settings_floating_queue", "mod_settings_floating_queue_desc", Type.INTEGER),
    MSG_DELETE_STRATEGY("mod_msg_delete_strategy_type", "mod_settings_msg_delete", "mod_settings_msg_delete_desc", Type.INTEGER),
    PLAYER_FLOATING_CHAT("mod_floating_chat", "mod_settings_floating_chat", "mod_settings_floating_chat_desc", Type.BOOLEAN),
    ROBOTTY_SERVICE("mod_message_history", "mod_settings_message_history", "mod_settings_message_history_desc", Type.BOOLEAN),
    ROBOTTY_LIMIT("mod_robotty_limit_value", "mod_settings_message_history_limit", "mod_settings_message_history_limit_desc", Type.INTEGER),
    USER_FILTER_TEXT("mod_filter_text", "mod_settings_blocklist", "mod_settings_blocklist_desc", Type.STRING),
    COMPACT_PLAYER_FOLLOW_VIEW("mod_compact_follow_view", "mod_settings_compact_follow_view", "mod_settings_compact_follow_view_desc", Type.BOOLEAN),
    PLAYER_REFRESH_BUTTON("mod_player_show_refresh_button", "mod_settings_show_refresh_button", "mod_settings_show_refresh_button_desc", Type.BOOLEAN),
    PLAYER_STAT_BUTTON("mod_player_show_stats_button", "mod_settings_show_stats_button", "mod_settings_show_stats_button_desc", Type.BOOLEAN),
    HIDE_CHAT_RESTRICTION("mod_hide_chat_restrictions", "mod_settings_hide_chat_restriction", "mod_settings_hide_chat_restriction_desc", Type.BOOLEAN),
    HIDE_CHAT_HEADER("mod_hide_chat_header", "mod_settings_hide_chat_header_container", "mod_settings_hide_chat_header_container_desc", Type.BOOLEAN),
    SHOW_WIDE_EMOTES("mod_fix_wide_emotes", "mod_settings_wide_emotes", "mod_settings_wide_emotes_desc", Type.BOOLEAN),
    DISABLE_GOOGLE_BILLING("mod_disable_google_billing", "mod_settings_force_disable_google_billing", "mod_settings_force_disable_google_billing_desc", Type.BOOLEAN),
    SWIPPER_LOCK_BUTTON("mod_show_swipper_lock_button", "mod_settings_swipper_lock_button", "mod_settings_swipper_lock_button_desc", Type.BOOLEAN),
    PLAYER_FORWARD_SEEK("mod_player_forward_seek_value", "mod_settings_player_forward_seek", "mod_settings_player_forward_seek_desc", Type.INTEGER),
    PLAYER_BACKWARD_SEEK("mod_player_backward_seek_value", "mod_settings_player_backward_seek", "mod_settings_player_backward_seek_desc", Type.INTEGER),
    EXOPLAYER_SPEED("mod_player_exoplayer_speed", "mod_settings_exoplayer_speed", "mod_settings_exoplayer_speed_desc", Type.INTEGER),
    CHAT_MESSAGE_FONT_SIZE("mod_chat_message_font_size_value", "mod_settings_chat_message_font_size", "mod_settings_chat_message_font_size_desc", Type.INTEGER),
    AUTOCLICKER("mod_chat_autoclicker", "mod_settings_autoclicker", "mod_settings_autoclicker_desc", Type.BOOLEAN),
    SHOW_HYPE_TRAIN("mod_show_hype_train", "mod_settings_show_hype_train", "mod_settings_show_hype_train_desc", Type.BOOLEAN),
    STREAM_UPTIME("mod_player_show_stream_uptime", "mod_settings_show_stream_uptime", "mod_settings_show_stream_uptime_desc", Type.BOOLEAN),
    LAST_BUILD_NUMBER("mod_last_build_number", Type.INTEGER),
    FFZ_BADGES("mod_ffz_badges", Type.BOOLEAN),
    SURESTREAM_ADBLOCK("mod_surestream_adblock_v2_type", "mod_settings_surestream_adblock_var", "mod_settings_surestream_adblock_var_desc", Type.STRING);

    private static final Map<String, Preferences> s_Map = new HashMap<>();

    static {
        for (Preferences preference : EnumSet.allOf(Preferences.class)) {
            s_Map.put(preference.getKey(), preference);
        }
    }

    public final String mKey;
    public final String mTitle;
    public final String mDescription;
    public final Type mType;

    public enum Type {
        BOOLEAN,
        INTEGER,
        STRING
    }

    Preferences(String key, Type type) {
        this(key, null, null, type);
    }

    Preferences(String key, String title, String description, Type type) {
        mKey = key;
        mTitle = title;
        mDescription = description;
        mType = type;
    }

    public String getKey() {
        return mKey;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Type getType() {
        return mType;
    }

    public static Preferences lookupKey(String key) {
        return s_Map.get(key);
    }
}
