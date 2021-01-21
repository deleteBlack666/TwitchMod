package tv.twitch.android.mod.badge;


import tv.twitch.android.mod.emote.UrlProvider;
import tv.twitch.android.mod.models.chat.Badge;


public class DonatorBadge implements Badge {
    private final UrlProvider urlProvider;

    public DonatorBadge(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return "donator";
    }

    @Override
    public UrlProvider getUrlProvider() {
        return urlProvider;
    }
}
