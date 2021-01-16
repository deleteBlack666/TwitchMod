package tv.twitch.android.network.retrofit;


import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tv.twitch.android.mod.hooks.Hook;


public class ApiRequestInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) {
        /* ... */

        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();

        /* ... */

        Hook.tryPatchAdRequest(request, newBuilder);  // TODO: __INJECT_CODE

        /* ... */

        // return chain.proceed(newBuilder.build());
        return null;
    }

}
