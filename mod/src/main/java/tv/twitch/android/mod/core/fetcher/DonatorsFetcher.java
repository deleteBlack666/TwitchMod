package tv.twitch.android.mod.core.fetcher;


import android.text.TextUtils;

import retrofit2.Call;
import tv.twitch.android.mod.badge.BadgeSetImpl;
import tv.twitch.android.mod.models.chat.DonatorBadge;
import tv.twitch.android.mod.bridge.ApiCallback;
import tv.twitch.android.mod.emote.SimpleUrlProvider;
import tv.twitch.android.mod.emote.UrlProvider;
import tv.twitch.android.mod.models.api.DonatorsResponse;
import tv.twitch.android.mod.models.api.FailReason;
import tv.twitch.android.mod.models.chat.BadgeSet;
import tv.twitch.android.mod.util.Logger;

import static tv.twitch.android.mod.net.ServiceFactory.getTMApi;


public class DonatorsFetcher extends ApiCallback<DonatorsResponse> {
    private final DonatorsFetcher.Callback mCallback;

    public interface Callback {
        void onDonatorsFetched(BadgeSet set);
    }

    public DonatorsFetcher(DonatorsFetcher.Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void fetch() {
        Logger.info("Fetching donators badges...");
        getTMApi().getDonators().enqueue(this);
    }

    @Override
    public void onRequestSuccess(DonatorsResponse donatorsResponse) {
        if (donatorsResponse == null) {
            Logger.error("response is null");
            return;
        }

        if (TextUtils.isEmpty(donatorsResponse.getBadgeUrl())) {
            Logger.error("empty badgeUrl");
            return;
        }

        if (donatorsResponse.getIds() == null) {
            Logger.error("ids == null");
            return;
        }

        UrlProvider donProvider = new SimpleUrlProvider(donatorsResponse.getBadgeUrl());
        BadgeSetImpl set = new BadgeSetImpl();
        for (Integer id : donatorsResponse.getIds()) {
            if (id == null)
                continue;

            set.addBadge(new DonatorBadge(donProvider), id);
        }

        mCallback.onDonatorsFetched(set);
    }

    @Override
    public void onRequestFail(Call<DonatorsResponse> call, FailReason failReason) {
        Logger.debug("reason="+failReason.toString());
    }
}
