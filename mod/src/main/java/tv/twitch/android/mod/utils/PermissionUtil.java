package tv.twitch.android.mod.utils;


import android.Manifest;
import android.content.Context;

import tv.twitch.android.mod.bridges.dexter.Dexter;
import tv.twitch.android.mod.bridges.dexter.PermissionToken;
import tv.twitch.android.mod.bridges.dexter.listener.DexterError;
import tv.twitch.android.mod.bridges.dexter.listener.PermissionDeniedResponse;
import tv.twitch.android.mod.bridges.dexter.listener.PermissionGrantedResponse;
import tv.twitch.android.mod.bridges.dexter.listener.PermissionRequest;
import tv.twitch.android.mod.bridges.dexter.listener.PermissionRequestErrorListener;
import tv.twitch.android.mod.bridges.dexter.listener.single.PermissionListener;


public class PermissionUtil {
    private static final String WRITE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;


    public interface ResultCallback {
        void onPermissionGranted();

        void onPermissionDenied();

        void onError();
    }

    public static void checkWritePermission(Context context, final ResultCallback callback) {
        Dexter.withContext(context)
                .withPermission(WRITE_PERMISSION)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        Logger.debug(response.getPermissionName());
                        callback.onPermissionGranted();
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        Helper.showToast("PermissionDenied");
                        Logger.debug(response.getPermissionName());
                        callback.onPermissionDenied();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        Logger.debug(permission.getName());
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override public void onError(DexterError error) {
                        Logger.debug("There was an error: " + error.toString());
                        callback.onError();
                    }
                })
                .check();
    }
}
