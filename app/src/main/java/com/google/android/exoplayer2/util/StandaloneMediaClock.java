package com.google.android.exoplayer2.util;


import com.google.android.exoplayer2.PlaybackParameters;

import tv.twitch.android.mod.bridges.Hooks;

public class StandaloneMediaClock {
    private PlaybackParameters playbackParameters;

    StandaloneMediaClock() {
        playbackParameters = Hooks.hookVodPlayerStandaloneMediaClockInit();
    }
}
