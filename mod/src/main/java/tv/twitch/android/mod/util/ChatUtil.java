package tv.twitch.android.mod.util;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.util.LruCache;
import android.util.Pair;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import tv.twitch.android.mod.bridge.interfaces.IChatMessageFactory;
import tv.twitch.android.mod.bridge.interfaces.ILiveChatSource;
import tv.twitch.android.mod.chat.fetcher.RobottyFetcher;
import tv.twitch.android.mod.emotes.EmoteManager;
import tv.twitch.android.mod.models.chat.Badge;
import tv.twitch.android.mod.models.chat.Emote;
import tv.twitch.android.mod.models.preferences.ImageSize;
import tv.twitch.android.models.channel.ChannelInfo;
import tv.twitch.android.shared.chat.ChatMessageInterface;
import tv.twitch.android.shared.chat.util.ClickableUsernameSpan;
import tv.twitch.chat.ChatEmoticonToken;
import tv.twitch.chat.ChatMentionToken;
import tv.twitch.chat.ChatMessageInfo;
import tv.twitch.chat.ChatMessageToken;
import tv.twitch.chat.ChatTextToken;
import tv.twitch.chat.ChatUrlToken;


public class ChatUtil {
    private static final String TIMESTAMP_DATE_FORMAT = "HH:mm";
    private static final float TIMESTAMP_SPAN_PROPORTIONAL = 0.75f;

    private static final int MAX_THEME_CACHE_SIZE = 200;
    private static final float DARK_THEME_NICKNAME_CHECK = .3f;
    private static final float DARK_THEME_NICKNAME_FIXED = .4f;
    private static final float WHITE_THEME_NICKNAME_CHECK = .9f;
    private static final float WHITE_THEME_NICKNAME_FIXED = .8f;


    private static final LruCache<Integer, Integer> mDarkThemeCache = new LruCache<Integer, Integer>(MAX_THEME_CACHE_SIZE) {
        @Override
        protected Integer create(Integer color) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            if (hsv[2] >= DARK_THEME_NICKNAME_CHECK) {
                return color;
            }

            hsv[2] = DARK_THEME_NICKNAME_FIXED;
            return Color.HSVToColor(hsv);
        }
    };

    private static final LruCache<Integer, Integer> mDefaultThemeCache = new LruCache<Integer, Integer>(MAX_THEME_CACHE_SIZE) {
        @Override
        protected Integer create(Integer color) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            if (hsv[2] <= WHITE_THEME_NICKNAME_CHECK) {
                return color;
            }

            hsv[2] = WHITE_THEME_NICKNAME_FIXED;
            return Color.HSVToColor(hsv);
        }
    };


    public static void tryAddRecentMessages(final ILiveChatSource source, final ChannelInfo channelInfo, int limit) {
        if (source == null) {
            Logger.error("source is null");
            return;
        }

        if (channelInfo == null) {
            Logger.error("channelInfo is null");
            return;
        }

        int channelId = channelInfo.getId();

        if (channelId <= 0) {
            Logger.error("Bad ID: " + channelId);
            return;
        }

        source.addRecentMessage("[ROBOTTY] Fetching recent messages... (https://recent-messages.robotty.de)");
        RobottyFetcher fetcher = new RobottyFetcher(channelInfo, limit, new RobottyFetcher.Callback() {
            @Override
            public void onMessagesParsed(ChannelInfo channel, List<String> ircMessages) {
                if (channel == null) {
                    Logger.error("channel is null");
                    return;
                }

                List<String> messages = new ArrayList<>();
                for (int j = ircMessages.size() - 1; j >= 0; j--) {
                    String chatMessageText = IrcUtil.formatIrcMessage(ircMessages.get(j));
                    if (chatMessageText != null) {
                        messages.add(0, chatMessageText);
                    }
                }

                if (messages.size() == 0) {
                    source.addRecentMessage("[ROBOTTY] No message available");
                    return;
                }

                for (String msg : messages) {
                    source.addRecentMessage(msg);
                }
            }

            @Override
            public void onError(ChannelInfo info, String text) {
                if (!TextUtils.isEmpty(text)) {
                    Logger.error(text);
                    source.addRecentMessage("[ROBOTTY] Error: " + text);
                }
            }
        });
        fetcher.fetch();
    }

    public static Spanned createDeletedStrikethroughSpanFromChatMessageSpan(Spanned msg) {
        try {
            if (TextUtils.isEmpty(msg))
                return msg;

            StrikethroughSpan[] strikethroughSpanArr = msg.getSpans(0, msg.length(), StrikethroughSpan.class);
            if (strikethroughSpanArr != null && strikethroughSpanArr.length > 0)
                return msg;

            ClickableUsernameSpan[] clickableUsernameSpanArr = msg.getSpans(0, msg.length(), ClickableUsernameSpan.class);
            int usernameSpannedEnd = 0;
            if (clickableUsernameSpanArr.length != 0) {
                usernameSpannedEnd = msg.getSpanEnd(clickableUsernameSpanArr[0]);
                int i = usernameSpannedEnd + 2;
                if (i < msg.length()) {
                    if (msg.subSequence(usernameSpannedEnd, i).toString().equalsIgnoreCase(": ")) {
                        usernameSpannedEnd = i;
                    }
                }
            }

            SpannableStringBuilder msgBuilder = new SpannableStringBuilder(msg, 0, usernameSpannedEnd);

            SpannableStringBuilder msgTextBuilder = new SpannableStringBuilder(msg, usernameSpannedEnd, msg.length());
            msgTextBuilder.setSpan(new StrikethroughSpan(), 0, msgTextBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            msgBuilder.append(msgTextBuilder);

            return SpannedString.valueOf(msgBuilder);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return msg;
    }

    @NonNull
    public static List<Pair<String, Integer>> splitByWords(CharSequence message) {
        final List<Pair<String, Integer>> words = new ArrayList<>();

        if (TextUtils.isEmpty(message))
            return words;

        boolean newWord = false;
        int startPos = 0;
        int messageLength = message.length();

        for (int currentPos = 0; currentPos <= messageLength; currentPos++) {
            if (currentPos != messageLength && message.charAt(currentPos) != ' ') {
                if (!newWord) {
                    newWord = true;
                    startPos = currentPos;
                }
            } else {
                if (newWord) {
                    newWord = false;
                    final String word = TextUtils.substring(message, startPos, currentPos);
                    words.add(new Pair<>(word, startPos));
                }
            }
        }

        return words;
    }


    public static List<Pair<Emote, Integer>> getBttvEmotes(SpannedString messageSpan, final int channelID) {
        List<Pair<Emote, Integer>> bttvEmotes = new ArrayList<>();

        if (TextUtils.isEmpty(messageSpan)) {
            return bttvEmotes;
        }

        for (Pair<String, Integer> word : splitByWords(messageSpan)) {
            final String emoteCode = word.first;
            final Emote emote = EmoteManager.INSTANCE.findEmote(emoteCode, channelID);
            if (emote != null) {
                bttvEmotes.add(new Pair<>(emote, word.second));
            }
        }

        return bttvEmotes;
    }

    public static SpannedString tryAddBadges(SpannedString messageBadgeSpan, IChatMessageFactory factory, Collection<Badge> badges) {
        if (badges == null || badges.isEmpty())
            return messageBadgeSpan;

        SpannableStringBuilder ssb = messageBadgeSpan == null ? new SpannableStringBuilder() : new SpannableStringBuilder(messageBadgeSpan);

        for (Badge badge : badges) {
            if (badge == null)
                continue;

            String url = badge.getUrlProvider().getUrl(ImageSize.LARGE);
            CharSequence newBadgeSpan = factory.getSpannedBadge(url, badge.getName());
            if (TextUtils.isEmpty(newBadgeSpan))
                continue;

            if (badge.getReplaces().length != 0) {
                Logger.debug("FIX!"); // FIXME: rewrite
            } else {
                ssb.append(newBadgeSpan);
            }
        }

        return new SpannedString(ssb);
    }

    public static SpannedString tryAddEmotes(final IChatMessageFactory factory, ChatMessageInterface chatMessageInterface, SpannedString messageSpan, final int channelID, final boolean isGifsDisabled, final @ImageSize String emoteSize) {
        if (factory == null) {
            Logger.error("factory is null");
            return messageSpan;
        }

        if (TextUtils.isEmpty(messageSpan)) {
            return messageSpan;
        }

        List<Pair<Emote, Integer>> bttvEmotes = getBttvEmotes(messageSpan, channelID);
        if (bttvEmotes.size() == 0)
            return messageSpan;

        SpannableStringBuilder ssb = new SpannableStringBuilder(messageSpan);

        for (Pair<Emote, Integer> emote : bttvEmotes) {
            final Emote emoteModel = emote.first;
            final Integer startPos = emote.second;
            final String emoteCode = emoteModel.getCode();

            if (emoteModel.isGif() && isGifsDisabled)
                continue;

            String url = emoteModel.getUrlProvider().getUrl(emoteSize);
            if (url == null)
                continue;

            SpannableString emoteSpan = (SpannableString) factory.getSpannedEmote(url, emoteCode);
            if (emoteSpan != null) {
                ssb.replace(startPos, startPos + emoteCode.length(), emoteSpan);
            }
        }

        return SpannedString.valueOf(ssb);
    }

    public static Spanned formatTimestamp(Spanned message, CharSequence timestamp) {
        SpannableString timestampSpan = SpannableString.valueOf(timestamp);
        timestampSpan.setSpan(new ScaleXSpan(0.8f), 0, timestampSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return SpannableString.valueOf(new SpannableStringBuilder(timestampSpan).append(" ").append(new SpannableStringBuilder(message)));
    }

    public static Spanned createOffsetTimestampSpanFromChatMessageSpan(Spanned message, int seconds) {
        return formatTimestamp(message, DateUtils.formatElapsedTime(seconds));
    }

    public static Spanned createTimestampSpanFromChatMessageSpan(Spanned message, final Date date) {
        return formatTimestamp(message, new SimpleDateFormat(TIMESTAMP_DATE_FORMAT, Locale.UK).format(date));
    }

    public static Integer fixUsernameColor(int color, final boolean isDarkTheme) {
        return isDarkTheme ? mDarkThemeCache.get(color) : mDefaultThemeCache.get(color);
    }

    static String getTextFromToken(ChatMessageToken token) {
        if (token instanceof ChatUrlToken) {
            if (!((ChatUrlToken) token).hidden)
                return ((ChatUrlToken) token).url;
        } else if (token instanceof ChatTextToken) {
            return ((ChatTextToken) token).text;
        } else if (token instanceof ChatMentionToken) {
            return ((ChatMentionToken) token).text;
        } else if (token instanceof ChatEmoticonToken) {
            return ((ChatEmoticonToken) token).emoticonText;
        }

        return null;
    }

    public static boolean isMentionUser(ChatMessageInfo chatMessageInfo, String userName) {
        if (TextUtils.isEmpty(userName))
            return false;

        if (chatMessageInfo == null || chatMessageInfo.tokens == null)
            return false;

        ChatMessageToken[] tokens = chatMessageInfo.tokens;

        for (ChatMessageToken messageToken : tokens) {
            if (messageToken instanceof ChatMentionToken) {
                ChatMentionToken mentionToken = (ChatMentionToken) messageToken;
                String mentionUser = mentionToken.userName;
                if (TextUtils.isEmpty(mentionUser))
                    continue;

                if (mentionUser != null && mentionUser.equalsIgnoreCase(userName)) {
                    return true;
                }
            }
        }

        return false;
    }
}
