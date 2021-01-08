package tv.twitch.android.mod.models.api;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


import java.util.HashMap;


public class FfzBadge {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @Nullable
    @SerializedName("replaces")
    private String replaces;
    @SerializedName("urls")
    private FfzUrls urls;

    public int getId() {
        return id;
    }

    public FfzUrls getUrls() {
        return urls;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getReplaces() {
        return replaces;
    }
}
