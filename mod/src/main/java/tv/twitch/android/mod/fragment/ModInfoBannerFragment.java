package tv.twitch.android.mod.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


import tv.twitch.android.mod.bridge.LoaderLS;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.preference.PreferenceManager;
import tv.twitch.android.mod.util.Helper;


public class ModInfoBannerFragment extends DialogFragment {
    private static final String TELEGRAM_URL = "https://t.me/pubTw";
    private static final String GITHUB_URL = "https://github.com/nopbreak/TwitchMod";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SpannableString title = new SpannableString("TwitchMod v" + LoaderLS.getVersionName());
        title.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0,
                title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(createViewDialog(getContext()))
                .create();
    }

    private View createViewDialog(Context context) {
        View view = LayoutInflater.from(getContext())
                .inflate(ResourcesManager.getLayoutId("fragment_mod_info_dialog"), null, false);
        ImageView imTelegram = view.findViewById(ResourcesManager.getId("dialog_mod_info__telegram"));
        ImageView imGithub = view.findViewById(ResourcesManager.getId("dialog_mod_info__github"));
        TextView tvModInfoText = view.findViewById(ResourcesManager.getId("dialog_mod_info__text"));
        TextView tvDismiss = view.findViewById(ResourcesManager.getId("dialog_mod_info__dismiss"));

        imTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.openUrl(TELEGRAM_URL);
            }
        });

        imGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.openUrl(GITHUB_URL);
            }
        });

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                PreferenceManager.INSTANCE.updateLastBuildNum(LoaderLS.getBuildNumber());
            }
        });

        final String changelog = LoaderLS.getInstance().getChangelog();
        if (!TextUtils.isEmpty(changelog))
            tvModInfoText.setText(changelog);

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        PreferenceManager.INSTANCE.setBannerShown(true);
    }
}
