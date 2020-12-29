package tv.twitch.android.mod.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tv.twitch.android.mod.bridges.ResourcesManager;
import tv.twitch.android.mod.bridges.interfaces.ISharedPanelWidget;
import tv.twitch.android.models.clips.ClipModel;
import tv.twitch.android.models.clips.ClipQualityOption;


public class ClipDownloader implements View.OnClickListener {
    private final ISharedPanelWidget mWidget;
    private final ClipsAdapter mAdapter;

    public ClipDownloader(ISharedPanelWidget sharedPanelWidget) {
        mWidget = sharedPanelWidget;
        mAdapter = new ClipsAdapter(mWidget.getLayout().getContext());
    }

    private void fillAdapter(List<ClipQualityOption> options) {
        if (options == null) {
            Logger.error("options is null");
            return;
        }

        mAdapter.clear();
        for (ClipQualityOption option : options) {
            ClipsAdapter.ClipItem item = new ClipsAdapter.ClipItem(option.getSource(), option.getQuality(), String.valueOf(option.getFrameRate()));
            mAdapter.add(item);
        }
    }

    public static final class ClipsAdapter extends ArrayAdapter<ClipsAdapter.ClipItem> {
        public static final class ClipItem {
            private final String mUrl;
            private final String mQuality;
            private final String mFrameRate;
            private int mSize;

            public ClipItem(String url, String qual, String rate) {
                mUrl = url;
                mQuality = qual;
                mFrameRate = rate;
            }

            @Override
            public String toString() {
                return mQuality + "p" + mFrameRate;
            }

            public String getUrl() {
                return mUrl;
            }

            public String getSize() {
                if (mSize <= 0)
                    return "unknown";

                return mSize / 1024  + " kb";
            }

            public void setSize(int size) {
                if (size < 0)
                    mSize = -1;

                mSize = size;
            }

            public String getFormattedQuality() {
                return getQuality() + "p" + getFrameRate();
            }

            public String getQuality() {
                return mQuality;
            }

            public String getFrameRate() {
                return mFrameRate;
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final ClipItem item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(ResourcesManager.getLayoutId("mod_download_item"), parent, false);
                final TextView clipQualityView = convertView.findViewById(ResourcesManager.getId("clip_quality"));
                final TextView clipSizeView = convertView.findViewById(ResourcesManager.getId("clip_size"));

                clipQualityView.setText(item.getFormattedQuality());
                clipSizeView.setText(item.getSize());

                ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
                final Handler handler = new Handler(Looper.getMainLooper());

                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int fileLength = Helper.getFileLength(item.getUrl());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                item.setSize(fileLength);
                                clipSizeView.setText(item.getSize());
                            }
                        });
                    }
                });
            }

            return convertView;
        }

        public ClipsAdapter(@NonNull Context context) {
            super(context, ResourcesManager.getLayoutId("twitch_spinner_dropdown_item"));
        }
    }

    @Override
    public void onClick(View v) {
        ClipModel clipModel = mWidget.getClipModel();
        if (clipModel == null) {
            Logger.error("clipModel is null");
            return;
        }

        fillAdapter(mWidget.getClipModel().getQualityOptions());
        AlertDialog.Builder builder = new AlertDialog.Builder(mWidget.getLayout().getContext());
        builder.setTitle(clipModel.getTitle());

        builder.setAdapter(mAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipsAdapter.ClipItem option = mAdapter.getItem(which);
                if (option == null) {
                    Logger.debug("option is null. Pos=" + which);
                    return;
                }

                ClipModel clipModel = mWidget.getClipModel();

                String filename = clipModel.getTitle() + " - " + clipModel.getBroadcasterName() + " - " + clipModel.getClipSlugId() + " - " + option.mQuality;
                Helper.downloadMP4File(mWidget.getLayout().getContext(), option.getUrl(), filename);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        mWidget.hidePanel();
    }
}
