package tv.twitch.android.mod.utils;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import tv.twitch.android.app.core.ActivityUtil;


public class FragmentUtil {

    public static void showDialogFragment(Context context, DialogFragment fragment, String tag) {
        if (context == null) {
            Logger.error("context is null");
            return;
        }

        if (fragment == null) {
            Logger.error("fragment is null");
            return;
        }

        AppCompatActivity activity = (AppCompatActivity) context;

        if (ActivityUtil.isActivityInvalid(activity)) {
            Logger.error("invalid activity");
            return;
        }

        fragment.show(activity.getSupportFragmentManager(), tag);
    }
}
