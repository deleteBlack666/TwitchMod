package tv.twitch.android.mod.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import tv.twitch.android.app.core.ActivityUtil;
import tv.twitch.android.mod.bridge.ResourcesManager;
import tv.twitch.android.mod.util.Logger;


public class SleepTimerFragment extends DialogFragment implements TimePicker.OnTimeChangedListener {
    private static final int DEFAULT_HOUR = 0;
    private static final int DEFAULT_MINUTE = 15;

    private int currentHour = DEFAULT_HOUR;
    private int currentMinute = DEFAULT_MINUTE;

    public interface SleepTimerListener {
        void onTimeChanged(int hour, int minute);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(createViewDialog(getContext()));
        dialog.create();

        return dialog;
    }

    private View createViewDialog(Context context) {
        View view = LayoutInflater.from(context)
                .inflate(ResourcesManager.getLayoutId("fragment_sleep_timer_dialog"),
                        null, false);

        TimePicker timePicker = view.findViewById(ResourcesManager.getId("mod_time_picker__time_picker"));
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);
        timePicker.setCurrentHour(DEFAULT_HOUR);
        timePicker.setCurrentMinute(DEFAULT_MINUTE);

        final TextView cancel = view.findViewById(ResourcesManager.getId("mod_time_picker__cancel"));
        final TextView ok = view.findViewById(ResourcesManager.getId("mod_time_picker__ok"));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Activity activity = getActivity();
                    if (activity == null) {
                        Logger.error("activity is null");
                        return;
                    }
                    if (ActivityUtil.isActivityInvalid(activity)) {
                        Logger.error("invalid activity");
                        return;
                    }

                    if (!(activity instanceof SleepTimerListener)) {
                        Logger.error("bad activity");
                        return;
                    }
                    SleepTimerListener sleepTimerListener = (SleepTimerListener) activity;

                    sleepTimerListener.onTimeChanged(currentHour, currentMinute);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                } finally {
                    dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        currentHour = hourOfDay;
        currentMinute = minute;
    }
}
