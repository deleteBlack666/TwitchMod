package tv.twitch.android.network;


import tv.twitch.android.buildconfig.BuildConfigUtil;
import tv.twitch.android.mod.hooks.Jump;


public class OkHttpClientFactory {
    /* ... */

    public OkHttpClientFactory(Object cookieInterceptor, Object apiRequestInterceptor, Object userAgentInterceptor) {
        /* ... */

        if (BuildConfigUtil.INSTANCE.isDebugConfigEnabled() && Jump.isInterceptorOn()) { // TODO:__JUMP_HOOK
            /* ... */
        }

        /* ... */
    }

    /* ... */
}
