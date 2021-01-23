package tv.twitch.android.mod.models;


import androidx.annotation.NonNull;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Preference {
    // TRUE
    BTTV_EMOTES("mod_bttv_emotes", "mod_settings_bttv_emotes", "mod_settings_bttv_emotes_desc", new BooleanValue(Boolean.TRUE)),
    PLAYER_ADBLOCK("mod_player_adblock", "mod_settings_player_adblock", "mod_settings_player_adblock_desc", new BooleanValue(Boolean.TRUE)),
    PLAYER_REFRESH_BUTTON("mod_player_show_refresh_button", "mod_settings_show_refresh_button", "mod_settings_show_refresh_button_desc", new BooleanValue(Boolean.TRUE)),
    PLAYER_STAT_BUTTON("mod_player_show_stat_button", "mod_settings_show_stat_button", "mod_settings_show_stat_button_desc", new BooleanValue(Boolean.TRUE)),
    AUTOCLICKER("mod_chat_autoclicker", "mod_settings_autoclicker", "mod_settings_autoclicker_desc", new BooleanValue(Boolean.TRUE)),
    SHOW_HYPE_TRAIN("mod_chat_hype_train", "mod_settings_show_hype_train", "mod_settings_show_hype_train_desc", new BooleanValue(Boolean.TRUE)),
    STREAM_UPTIME("mod_player_show_stream_uptime", "mod_settings_show_stream_uptime", "mod_settings_show_stream_uptime_desc", new BooleanValue(Boolean.TRUE)),

    // FALSE
    CHAT_TIMESTAMP("mod_chat_timestamp", "mod_settings_chat_timestamp", "mod_settings_chat_timestamp_desc", new BooleanValue(Boolean.FALSE)),
    VOLUME_GESTURE("mod_volume_gesture", "mod_settings_volume_gesture", "mod_settings_volume_gesture_desc", new BooleanValue(Boolean.FALSE)),
    BRIGHTNESS_GESTURE("mod_brightness_gesture", "mod_settings_brightness_gesture", "mod_settings_brightness_gesture_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_FOLLOW_RECOMMENDATIONS("mod_hide_follow_recommendations", "mod_settings_disable_follow_recommendations", "mod_settings_disable_follow_recommendations_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_FOLLOW_RESUME("mod_hide_follow_resume", "mod_settings_disable_follow_resume", "mod_settings_disable_follow_resume_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_FOLLOW_GAMES("mod_hide_follow_games", "mod_settings_disable_follow_games", "mod_settings_disable_follow_games_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_DISCOVER_TAB("mod_hide_discover_tab", "mod_settings_hide_discover", "mod_settings_hide_discover_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_ESPORTS_TAB("mod_hide_esports_tab", "mod_settings_hide_esports", "mod_settings_hide_esports_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_RECENT_SEARCH_RESULTS("mod_hide_recent_search_results", "mod_settings_hide_recent_search_results", "mod_settings_hide_recent_search_results_desc", new BooleanValue(Boolean.FALSE)),
    DEV_MODE("mod_dev_mode", "mod_settings_dev_mode", "mod_settings_dev_mode_desc", new BooleanValue(Boolean.FALSE)),
    DISABLE_THEATRE_AUTOPLAY("mod_disable_theatre_autoplay", "mod_settings_disable_theatre_autoplay", "mod_settings_disable_theatre_autoplay_desc", new BooleanValue(Boolean.FALSE)),
    FILTER_CHAT_SYSTEM("mod_filter_chat_system", "mod_settings_filter_chat_system", "mod_settings_filter_chat_system_desc", new BooleanValue(Boolean.FALSE)),
    DEV_INTERCEPTOR("mod_dev_interceptor", "mod_settings_interceptor", "mod_settings_interceptor_desc", new BooleanValue(Boolean.FALSE)),
    SHOW_CHAT_FOR_BANNED_USER("mod_bypass_chat_ban", "mod_settings_bypass_chat_ban", "mod_settings_bypass_chat_ban_desc", new BooleanValue(Boolean.FALSE)),
    CHAT_MENTION_HIGHLIGHT("mod_chat_mention_highlight", "mod_settings_chat_mention_highlight", "mod_settings_chat_mention_highlight_desc", new BooleanValue(Boolean.FALSE)),
    DISABLE_CLIPFINITY("mod_disable_clipfinity", "mod_settings_disable_clipfinity", "mod_settings_disable_clipfinity_desc", new BooleanValue(Boolean.FALSE)),
    PLAYER_FLOATING_CHAT("mod_floating_chat", "mod_settings_floating_chat", "mod_settings_floating_chat_desc", new BooleanValue(Boolean.FALSE)),
    MESSAGE_HISTORY("mod_message_history", "mod_settings_message_history", "mod_settings_message_history_desc", new BooleanValue(Boolean.FALSE)),
    COMPACT_PLAYER_FOLLOW_VIEW("mod_compact_player_follow_view", "mod_settings_compact_player_follow_view", "mod_settings_compact_player_follow_view_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_CHAT_RESTRICTION("mod_hide_chat_restrictions", "mod_settings_hide_chat_restriction", "mod_settings_hide_chat_restriction_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_CHAT_HEADER("mod_hide_chat_header", "mod_settings_hide_chat_header_container", "mod_settings_hide_chat_header_container_desc", new BooleanValue(Boolean.FALSE)),
    SHOW_WIDE_EMOTES("mod_show_wide_emotes", "mod_settings_wide_emotes", "mod_settings_wide_emotes_desc", new BooleanValue(Boolean.FALSE)),
    DISABLE_GOOGLE_BILLING("mod_disable_google_billing", "mod_settings_force_disable_google_billing", "mod_settings_force_disable_google_billing_desc", new BooleanValue(Boolean.FALSE)),
    GESTURES_LOCK_BUTTON("mod_show_gestures_lock_button", "mod_settings_gestures_lock_button", "mod_settings_gestures_lock_button_desc", new BooleanValue(Boolean.FALSE)),
    FFZ_BADGES("mod_ffz_badges", "mod_settings_ffz_badges", "mod_settings_ffz_badges_desc", new BooleanValue(Boolean.FALSE)),
    HIDE_BITS_BUTTON("mod_hide_bits_button", "mod_settings_hide_bits_button", "mod_settings_hide_bits_button_desc", new BooleanValue(Boolean.FALSE)),

    // TEXT
    USER_FILTER_TEXT("mod_filter_text", "mod_settings_blocklist", "mod_settings_blocklist_desc", new StringValue("")),

    // STRINGS
    GIFS_RENDER_TYPE("mod_gifs_render_type", "mod_settings_gifs_render", "mod_settings_gifs_render_desc", new StringValue("static")),
    EMOTE_SIZE("mod_emote_size_type", "mod_settings_emote_size", "mod_settings_emote_size_desc", new StringValue("medium")),
    PLAYER_IMPLEMENTATION("mod_player_impl_type", "mod_settings_player_implementation", "mod_settings_player_implementation_desc", new StringValue("auto")),
    CHAT_MESSAGE_FILTER_LEVEL("mod_chat_message_filter_level_type", "mod_settings_filtering_level", "mod_settings_filtering_level_desc", new StringValue("disabled")),
    MSG_DELETE_STRATEGY("mod_msg_delete_strategy_type", "mod_settings_msg_delete_strategy", "mod_settings_msg_delete_strategy_desc", new StringValue("default")),
    SURESTREAM_ADBLOCK("mod_s_adblock_type", "mod_settings_player_surestream_adblock", "mod_settings_player_surestream_adblock_desc", new StringValue("v1")),

    // INTEGERS
    MINIPLAYER_SCALE("mod_miniplayer_scale_value", "mod_settings_miniplayer_scale", "mod_settings_miniplayer_scale_desc", new IntegerValue(100)),
    LANDSCAPE_CHAT_SCALE("mod_chat_width_scale_value", "mod_settings_landscape_chat_width", "mod_settings_landscape_chat_width_desc", new IntegerValue(30)),
    LANDSCAPE_CHAT_SCALE_MAX("mod_chat_width_scale_max_value", "mod_settings_landscape_chat_width_max", "mod_settings_landscape_chat_width_max_desc",  new IntegerValue(50)),
    PLAYER_FLOATING_CHAT_SIZE("mod_floating_chat_size_value", "mod_settings_floating_chat_size", "mod_settings_floating_chat_size_desc", new IntegerValue(3)),
    MESSAGE_HISTORY_LIMIT("mod_message_history_limit_value", "mod_settings_message_history_limit", "mod_settings_message_history_limit_desc", new IntegerValue(20)),
    PLAYER_FORWARD_SEEK("mod_player_forward_seek_value", "mod_settings_player_forward_seek", "mod_settings_player_forward_seek_desc", new IntegerValue(30)),
    PLAYER_BACKWARD_SEEK("mod_player_backward_seek_value", "mod_settings_player_backward_seek", "mod_settings_player_backward_seek_desc", new IntegerValue(10)),
    EXOPLAYER_SPEED("mod_player_exoplayer_speed", "mod_settings_exoplayer_speed", "mod_settings_exoplayer_speed_desc", new IntegerValue(100)),
    CHAT_MESSAGE_FONT_SIZE("mod_chat_message_font_size_value", "mod_settings_chat_message_font_size", "mod_settings_chat_message_font_size_desc", new IntegerValue(13)),
    LAST_BUILD_NUMBER("mod_last_build_number", new IntegerValue(-1));

    private static final Map<String, Preference> s_KeyMap = new HashMap<>();
    private static final Map<String, Preference> s_NameMap = new HashMap<>();

    static {
        for (Preference preference : EnumSet.allOf(Preference.class)) {
            s_KeyMap.put(preference.getKey(), preference);
            s_NameMap.put(preference.name(), preference);
        }
    }

    public final String mKey;
    public final String mTitle;
    public final String mSummary;
    public final ValueHolder mValueHolder;

    public interface ValueHolder {
        Object getValue();
    }

    public static class BooleanValue implements ValueHolder {
        private final Boolean val;

        public BooleanValue(Object bool) {
            if (bool == null) {
                val = false;
            } else if (bool instanceof Boolean) {
                val = (Boolean) bool;
            } else if (bool instanceof String) {
                val = Boolean.parseBoolean((String) bool);
            } else {
                val = Boolean.parseBoolean(bool.toString());
            }
        }

        @Override
        public Boolean getValue() {
            return val;
        }
    }

    public static class IntegerValue implements ValueHolder {
        private final Integer val;

        public IntegerValue(Object i) {
            if (i == null) {
                val = 0;
            } else if (i instanceof Integer) {
                val = (Integer) i;
            } else if (i instanceof String) {
                val = Integer.parseInt((String) i);
            } else {
                val = Integer.parseInt(i.toString());
            }
        }

        @Override
        public Integer getValue() {
            return val;
        }
    }

    public static class StringValue implements ValueHolder {
        private final String val;

        public StringValue(Object s) {
            if (s == null) {
                val = null;
            } else if (s instanceof String) {
                val = (String) s;
            } else {
                val = s.toString();
            }
        }

        @Override
        public String getValue() {
            return val;
        }
    }


    Preference(String key, ValueHolder val) {
        this(key, null, null, val);
    }

    Preference(String key, String title, String summary, ValueHolder val) {
        mKey = key;
        mTitle = title;
        mSummary = summary;
        mValueHolder = val;
    }

    @NonNull
    public String getKey() {
        return mKey;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    @NonNull
    public ValueHolder getDefaultValue() {
        return mValueHolder;
    }

    public static Preference lookupByKey(String key) {
        return s_KeyMap.get(key);
    }

    public static Preference lookupByName(String name) {
        return s_NameMap.get(name);
    }
}
