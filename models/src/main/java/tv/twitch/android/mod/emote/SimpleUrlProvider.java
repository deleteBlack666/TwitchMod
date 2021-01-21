package tv.twitch.android.mod.emote;


public class SimpleUrlProvider implements UrlProvider {
    private final String url;


    public SimpleUrlProvider(String url) {
        this.url = url;
    }

    @Override
    public String getUrl(String size) {
        return url;
    }
}
