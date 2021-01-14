package tv.twitch.android.mod.emote.fetcher;


import android.text.TextUtils;

import java.util.List;

import retrofit2.Call;
import tv.twitch.android.mod.bridge.ApiCallback;
import tv.twitch.android.mod.emote.BaseEmoteSet;
import tv.twitch.android.mod.emote.UrlProviderFactory;
import tv.twitch.android.mod.models.chat.BttvEmoteModel;
import tv.twitch.android.mod.models.chat.EmoteSet;
import tv.twitch.android.mod.models.api.BttvEmoteResponse;
import tv.twitch.android.mod.models.api.FailReason;
import tv.twitch.android.mod.models.api.ImageType;
import tv.twitch.android.mod.util.Logger;

import static tv.twitch.android.mod.net.ServiceFactory.getBttvApi;


public class BttvGlobalFetcher extends ApiCallback<List<BttvEmoteResponse>> {
    private final Callback mCallback;

    public interface Callback {
        void onGlobalBttvEmotesParsed(EmoteSet set);
    }

    public BttvGlobalFetcher(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onRequestSuccess(List<BttvEmoteResponse> bttvResponse) {
        BaseEmoteSet globalSet = new BaseEmoteSet();

        for (BttvEmoteResponse emoticon : bttvResponse) {
            if (emoticon == null)
                continue;

            if (TextUtils.isEmpty(emoticon.getId())) {
                continue;
            }

            if (TextUtils.isEmpty(emoticon.getCode())) {
                continue;
            }

            if (emoticon.getImageType() == null) {
                continue;
            }

            globalSet.addEmote(new BttvEmoteModel(emoticon.getCode(), emoticon.getId(),
                    emoticon.getImageType() == ImageType.GIF,
                    UrlProviderFactory.getBttvUrlProvider(emoticon.getId())));
        }

        mCallback.onGlobalBttvEmotesParsed(globalSet);
        Logger.debug("done!");
    }

    @Override
    public void onRequestFail(Call<List<BttvEmoteResponse>> call, FailReason failReason) {
        Logger.debug("reason="+failReason.toString());
    }

    @Override
    public void fetch() {
        Logger.info("Fetching global emotes...");
        getBttvApi().getGlobalEmotes().enqueue(this);
    }
}
