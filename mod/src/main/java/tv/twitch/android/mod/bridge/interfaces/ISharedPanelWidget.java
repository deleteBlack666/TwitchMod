package tv.twitch.android.mod.bridge.interfaces;


import android.widget.FrameLayout;

import tv.twitch.android.models.clips.ClipModel;



public interface ISharedPanelWidget {
    ClipModel getClipModel();

    void hidePanel();

    FrameLayout getLayout();
}
