package tv.twitch.android.mod.models;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Preferences {
    // TRUE
    BTTV_EMOTES("mod_bttv_emotes", "mod_settings_bttv_emotes", "mod_settings_bttv_emotes_desc", new DefValueImpl(Boolean.TRUE)),
    PLAYER_ADBLOCK("mod_player_adblock", "mod_settings_player_adblock", "mod_settings_player_adblock_desc", new DefValueImpl(Boolean.TRUE)),
    PLAYER_REFRESH_BUTTON("mod_player_show_refresh_button", "mod_settings_show_refresh_button", "mod_settings_show_refresh_button_desc", new DefValueImpl(Boolean.TRUE)),
    PLAYER_STAT_BUTTON("mod_player_show_stat_button", "mod_settings_show_stat_button", "mod_settings_show_stat_button_desc", new DefValueImpl(Boolean.TRUE)),
    AUTOCLICKER("mod_chat_autoclicker", "mod_settings_autoclicker", "mod_settings_autoclicker_desc", new DefValueImpl(Boolean.TRUE)),
    SHOW_HYPE_TRAIN("mod_chat_hype_train", "mod_settings_show_hype_train", "mod_settings_show_hype_train_desc", new DefValueImpl(Boolean.TRUE)),
    STREAM_UPTIME("mod_player_show_stream_uptime", "mod_settings_show_stream_uptime", "mod_settings_show_stream_uptime_desc", new DefValueImpl(Boolean.TRUE)),

    // FALSE
    CHAT_TIMESTAMP("mod_chat_timestamp", "mod_settings_chat_timestamp", "mod_settings_chat_timestamp_desc", new DefValueImpl(Boolean.FALSE)),
    VOLUME_GESTURE("mod_volume_gesture", "mod_settings_volume_gesture", "mod_settings_volume_gesture_desc", new DefValueImpl(Boolean.FALSE)),
    BRIGHTNESS_GESTURE("mod_brightness_gesture", "mod_settings_brightness_gesture", "mod_settings_brightness_gesture_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_FOLLOW_RECOMMENDATIONS("mod_hide_follow_recommendations", "mod_settings_disable_follow_recommendations", "mod_settings_disable_follow_recommendations_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_FOLLOW_RESUME("mod_hide_follow_resume", "mod_settings_disable_follow_resume", "mod_settings_disable_follow_resume_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_FOLLOW_GAMES("mod_hide_follow_games", "mod_settings_disable_follow_games", "mod_settings_disable_follow_games_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_DISCOVER_TAB("mod_hide_discover_tab", "mod_settings_hide_discover", "mod_settings_hide_discover_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_ESPORTS_TAB("mod_hide_esports_tab", "mod_settings_hide_esports", "mod_settings_hide_esports_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_RECENT_SEARCH_RESULTS("mod_hide_recent_search_results", "mod_settings_hide_recent_search_results", "mod_settings_hide_recent_search_results_desc", new DefValueImpl(Boolean.FALSE)),
    DEV_MODE("mod_dev_mode", "mod_settings_dev_mode", "mod_settings_dev_mode_desc", new DefValueImpl(Boolean.FALSE)),
    DISABLE_THEATRE_AUTOPLAY("mod_disable_theatre_autoplay", "mod_settings_disable_theatre_autoplay", "mod_settings_disable_theatre_autoplay_desc", new DefValueImpl(Boolean.FALSE)),
    FILTER_CHAT_SYSTEM("mod_filter_chat_system", "mod_settings_filter_chat_system", "mod_settings_filter_chat_system_desc", new DefValueImpl(Boolean.FALSE)),
    DEV_INTERCEPTOR("mod_dev_interceptor", "mod_settings_interceptor", "mod_settings_interceptor_desc", new DefValueImpl(Boolean.FALSE)),
    SHOW_CHAT_FOR_BANNED_USER("mod_bypass_chat_ban", "mod_settings_bypass_chat_ban", "mod_settings_bypass_chat_ban_desc", new DefValueImpl(Boolean.FALSE)),
    CHAT_MENTION_HIGHLIGHT("mod_chat_mention_highlight", "mod_settings_chat_mention_highlight", "mod_settings_chat_mention_highlight_desc", new DefValueImpl(Boolean.FALSE)),
    DISABLE_CLIPFINITY("mod_disable_clipfinity", "mod_settings_disable_clipfinity", "mod_settings_disable_clipfinity_desc", new DefValueImpl(Boolean.FALSE)),
    PLAYER_FLOATING_CHAT("mod_floating_chat", "mod_settings_floating_chat", "mod_settings_floating_chat_desc", new DefValueImpl(Boolean.FALSE)),
    MESSAGE_HISTORY("mod_message_history", "mod_settings_message_history", "mod_settings_message_history_desc", new DefValueImpl(Boolean.FALSE)),
    COMPACT_PLAYER_FOLLOW_VIEW("mod_compact_player_follow_view", "mod_settings_compact_player_follow_view", "mod_settings_compact_player_follow_view_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_CHAT_RESTRICTION("mod_hide_chat_restrictions", "mod_settings_hide_chat_restriction", "mod_settings_hide_chat_restriction_desc", new DefValueImpl(Boolean.FALSE)),
    HIDE_CHAT_HEADER("mod_hide_chat_header", "mod_settings_hide_chat_header_container", "mod_settings_hide_chat_header_container_desc", new DefValueImpl(Boolean.FALSE)),
    SHOW_WIDE_EMOTES("mod_show_wide_emotes", "mod_settings_wide_emotes", "mod_settings_wide_emotes_desc", new DefValueImpl(Boolean.FALSE)),
    DISABLE_GOOGLE_BILLING("mod_disable_google_billing", "mod_settings_force_disable_google_billing", "mod_settings_force_disable_google_billing_desc", new DefValueImpl(Boolean.FALSE)),
    GESTURES_LOCK_BUTTON("mod_show_gestures_lock_button", "mod_settings_gestures_lock_button", "mod_settings_gestures_lock_button_desc", new DefValueImpl(Boolean.FALSE)),
    FFZ_BADGES("mod_ffz_badges", "mod_settings_ffz_badges", "mod_settings_ffz_badges_desc", new DefValueImpl(Boolean.FALSE)),

    // TEXT
    USER_FILTER_TEXT("mod_filter_text", "mod_settings_blocklist", "mod_settings_blocklist_desc", new DefValueImpl("")),

    // STRINGS
    GIFS_RENDER_TYPE("mod_gifs_render_type", "mod_settings_gifs_render", "mod_settings_gifs_render_desc", new DefValueImpl("static")),
    EMOTE_SIZE("mod_emote_size_type", "mod_settings_emote_size", "mod_settings_emote_size_desc", new DefValueImpl("medium")),
    PLAYER_IMPLEMENTATION("mod_player_impl_type", "mod_settings_player_implementation", "mod_settings_player_implementation_desc", new DefValueImpl("auto")),
    CHAT_MESSAGE_FILTER_LEVEL("mod_chat_message_filter_level_type", "mod_settings_filtering_level", "mod_settings_filtering_level_desc", new DefValueImpl("disabled")),
    MSG_DELETE_STRATEGY("mod_msg_delete_strategy_type", "mod_settings_msg_delete_strategy", "mod_settings_msg_delete_strategy_desc", new DefValueImpl("default")),
    SURESTREAM_ADBLOCK("mod_s_adblock_type", "mod_settings_player_surestream_adblock", "mod_settings_player_surestream_adblock_desc", new DefValueImpl("v1")),

    // INTEGERS
    MINIPLAYER_SCALE("mod_miniplayer_scale_value", "mod_settings_miniplayer_scale", "mod_settings_miniplayer_scale_desc", new DefValueImpl(100)),
    LANDSCAPE_CHAT_SCALE("mod_chat_width_scale_value", "mod_settings_landscape_chat_width", "mod_settings_landscape_chat_width_desc", new DefValueImpl(30)),
    LANDSCAPE_CHAT_SCALE_MAX("mod_chat_width_scale_max_value", "mod_settings_landscape_chat_width_max", "mod_settings_landscape_chat_width_max_desc",  new DefValueImpl(50)),
    PLAYER_FLOATING_CHAT_SIZE("mod_floating_chat_size_value", "mod_settings_floating_chat_size", "mod_settings_floating_chat_size_desc", new DefValueImpl(3)),
    MESSAGE_HISTORY_LIMIT("mod_message_history_limit_value", "mod_settings_message_history_limit", "mod_settings_message_history_limit_desc", new DefValueImpl(20)),
    PLAYER_FORWARD_SEEK("mod_player_forward_seek_value", "mod_settings_player_forward_seek", "mod_settings_player_forward_seek_desc", new DefValueImpl(30)),
    PLAYER_BACKWARD_SEEK("mod_player_backward_seek_value", "mod_settings_player_backward_seek", "mod_settings_player_backward_seek_desc", new DefValueImpl(10)),
    EXOPLAYER_SPEED("mod_player_exoplayer_speed", "mod_settings_exoplayer_speed", "mod_settings_exoplayer_speed_desc", new DefValueImpl(100)),
    CHAT_MESSAGE_FONT_SIZE("mod_chat_message_font_size_value", "mod_settings_chat_message_font_size", "mod_settings_chat_message_font_size_desc", new DefValueImpl(13)),
    LAST_BUILD_NUMBER("mod_last_build_number", new DefValueImpl(-1));


    public interface DefaultValue {
        Object getValue();
    }

    public static class DefValueImpl implements DefaultValue {
        private final Object val;

        public DefValueImpl(Object obj) {
            val = obj;
        }

        public Object getValue() {
            return val.toString();
        }
    }

    private static final Map<String, Preferences> s_Map = new HashMap<>();

    static {
        for (Preferences preference : EnumSet.allOf(Preferences.class)) {
            s_Map.put(preference.getKey(), preference);
        }
    }

    public final String mKey;
    public final String mTitle;
    public final String mSummary;
    public final DefaultValue mDefaultValue;


    Preferences(String key, DefaultValue val) {
        this(key, null, null, val);
    }

    Preferences(String key, String title, String summary, DefaultValue val) {
        mKey = key;
        mTitle = title;
        mSummary = summary;
        mDefaultValue = val;
    }

    public String getKey() {
        return mKey;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    public DefaultValue getDefaultValue() {
        return mDefaultValue;
    }


    public static Preferences lookupByKey(String key) {
        return s_Map.get(key);
    }

    public static Preferences lookupByName(String name) {
        try {
            return Enum.valueOf(Preferences.class, name);
        } catch (IllegalArgumentException e) {
        }

        return null;
    }
}
