package tv.twitch.android.mod.models.api;


import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class FfzUrls {
    @SerializedName("1")
    private String small;

    @Nullable
    @SerializedName("2")
    private String medium;

    @Nullable
    @SerializedName("4")
    private String large;

    public String getSmall() {
        return small;
    }

    @Nullable
    public String getMedium() {
        return medium;
    }

    @Nullable
    public String getLarge() {
        return large;
    }
}
