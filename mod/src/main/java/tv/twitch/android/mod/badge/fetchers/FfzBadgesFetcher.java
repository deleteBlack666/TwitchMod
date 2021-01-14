package tv.twitch.android.mod.badge.fetchers;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import tv.twitch.android.mod.badge.FfzBadgeSet;
import tv.twitch.android.mod.bridge.ApiCallback;
import tv.twitch.android.mod.emote.UrlProviderFactory;
import tv.twitch.android.mod.models.api.FailReason;
import tv.twitch.android.mod.models.api.FfzBadge;
import tv.twitch.android.mod.models.api.FfzBadgesResponse;
import tv.twitch.android.mod.util.Logger;

import static tv.twitch.android.mod.net.ServiceFactory.getFfzApi;


public class FfzBadgesFetcher extends ApiCallback<FfzBadgesResponse> {
    private final Callback mCallback;

    public interface Callback {
        void onFfzBadgesParsed(FfzBadgeSet set);
    }

    public FfzBadgesFetcher(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onRequestSuccess(FfzBadgesResponse response) {
        if (response == null) {
            Logger.error("response is null");
            return;
        }

        List<FfzBadge> badges = response.getBadges();
        if (badges == null) {
            Logger.error("badges is null");
            return;
        }

        HashMap<Integer, List<Integer>> users = response.getUsers();
        if (users == null) {
            Logger.error("users is null");
            return;
        }

        FfzBadgeSet set = new FfzBadgeSet();
        for (FfzBadge badge : badges) {
            if (badge == null)
                continue;

            if (badge.getName() == null) {
                continue;
            }

            if (badge.getUrls() == null) {
                continue;
            }

            List<Integer> usersIds = users.get(badge.getId());
            if (usersIds == null || usersIds.isEmpty()) {
                continue;
            }

            for (Integer id : usersIds) {
                if (id == 0)
                    continue;

                set.addBadge(new tv.twitch.android.mod.models.chat.FfzBadge(badge.getName(),
                        UrlProviderFactory.getFfzUrlProvider(badge.getUrls()), badge.getReplaces()), id);
            }
        }

        mCallback.onFfzBadgesParsed(set);
    }

    @Override
    public void onRequestFail(Call<FfzBadgesResponse> call, FailReason failReason) {
        Logger.debug("reason="+failReason.toString());
    }

    @Override
    public void fetch() {
        Logger.info("Fetching ffz badges...");
        getFfzApi().getBadges().enqueue(this);
    }

    private static String getUrl(HashMap<Integer, String> urls) {
        String url;
        if (urls.containsKey(4)) {
            url = urls.get(4);
        } else if (urls.containsKey(2)) {
            url = urls.get(2);
        } else {
            url = urls.get(1);
        }

        if (TextUtils.isEmpty(url))
            return null;

        if (url != null && url.startsWith("//"))
            url = "https:" + url;

        return url;
    }
}
