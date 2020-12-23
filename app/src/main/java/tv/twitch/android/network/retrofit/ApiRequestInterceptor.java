package tv.twitch.android.network.retrofit;


import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tv.twitch.android.mod.bridges.Hooks;
import tv.twitch.android.mod.bridges.LoaderLS;
import tv.twitch.android.mod.utils.Helper;
import tv.twitch.android.mod.utils.Logger;


public class ApiRequestInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        /* ... */

        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        maybePatchRequest(request, newBuilder);  // TODO: __INJECT_CODE
        /* ... */
        return null;
    }

    private void maybePatchRequest(Request request, Request.Builder builder) { // TODO: __INJECT_METHOD
        if (Hooks.isSurestreamAdblockOn()) {
            if (Helper.isAccessTokenRequest(request)) {
                String authorization = LoaderLS.getAuthorization();
                if (!TextUtils.isEmpty(authorization)) {
                    Logger.debug("V1 ---- " + request.url());
                    builder.removeHeader("Authorization");
                    builder.addHeader("Authorization", LoaderLS.getAuthorization());
                }
            }
        } else if (Hooks.isSurestreamAdblockV2On()) {
            if (Helper.isGQLAccessTokenRequest(request) || Helper.isUsherRequest(request)) {
                Logger.debug("V2 ---- " + request.url());
                builder.removeHeader("referer");
                builder.addHeader("referer", "https://player.twitch.tv");
                builder.removeHeader("origin");
                builder.addHeader("origin", "https://player.twitch.tv");
            }
        }
    }
}
