package tv.twitch.android.mod.view;


import android.content.Context;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import tv.twitch.android.mod.bridges.interfaces.IStreamUptimeController;


public class StreamUptimeView extends androidx.appcompat.widget.AppCompatTextView implements IStreamUptimeController {
    private int mSeconds = 0;
    final Handler mHandler = new Handler();

    Runnable mTimer = new Runnable() {
        @Override
        public void run() {
            mSeconds++;
            drawTime();
            mHandler.postDelayed(mTimer, 1000);
        }
    };

    public StreamUptimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.textViewStyle);
    }

    public StreamUptimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        hide();
    }

    public StreamUptimeView(Context context) {
        this(context, null);
    }

    private void drawTime() {
        StreamUptimeView.this.setText(DateUtils.formatElapsedTime(mSeconds));
    }

    @Override
    public void show(int seconds) {
        destroyTimer();
        setupTimer(seconds);
        drawTime();
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        destroyTimer();
        setVisibility(GONE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroyTimer();
    }

    private void setupTimer(int seconds) {
        mSeconds = seconds;
        mHandler.post(mTimer);
    }

    private void destroyTimer() {
        mHandler.removeCallbacks(mTimer);
    }
}
