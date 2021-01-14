package tv.twitch.android.mod.hooks;


import tv.twitch.android.mod.setting.PreferenceManager;


public final class Jump {
    public static boolean shouldShowFollowedGames() {
        return PreferenceManager.INSTANCE.hideFollowGameSection();
    }

    public static boolean inDevMode() {
        return PreferenceManager.INSTANCE.isDevModeOn();
    }

    public static boolean isAdblockOn() {
        return PreferenceManager.INSTANCE.isPlayerAdblockOn();
    }

    public static boolean isAutoplayDisabled() {
        return PreferenceManager.INSTANCE.disableTheatreAutoplay();
    }

    public static boolean shouldHideDiscoverTab() {
        return PreferenceManager.INSTANCE.hideDiscoverTab();
    }

    public static boolean shouldHideEsportsTab() {
        return PreferenceManager.INSTANCE.hideEsportsTab();
    }

    public static boolean shouldShowChatHeader() {
        return !PreferenceManager.INSTANCE.shouldHideChatHeaderContainer();
    }

    public static boolean isGoogleBillingDisabled() {
        return PreferenceManager.INSTANCE.isGoogleBillingDisabled();
    }

    public static boolean isWideEmotesEnabled() {
        return PreferenceManager.INSTANCE.fixWideEmotes();
    }

    public static boolean isBypassChatBanEnabled() {
        return PreferenceManager.INSTANCE.showChatForBannedUser();
    }

    public static boolean shouldHideSystemMessages() {
        return PreferenceManager.INSTANCE.hideSystemMessagesInChat();
    }

    public static boolean shouldAnimateGifsInChat() {
        return PreferenceManager.INSTANCE.isGifsAnimated();
    }

    public static boolean isInterceptorOn() {
        return PreferenceManager.INSTANCE.isInterceptorEnabled();
    }

    public static boolean hideRecentSearch() {
        return PreferenceManager.INSTANCE.hideRecentSearchResult();
    }

    public static boolean shouldShowStreamUptime() {
        return PreferenceManager.INSTANCE.shouldShowStreamUptime();
    }

    public static boolean shouldShowStatButton() {
        return PreferenceManager.INSTANCE.shouldShowPlayerStatButton();
    }
}
