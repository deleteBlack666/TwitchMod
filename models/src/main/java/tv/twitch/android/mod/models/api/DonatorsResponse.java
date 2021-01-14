package tv.twitch.android.mod.models.api;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DonatorsResponse {
    @SerializedName("badgeUrl")
    String badgeUrl;
    @SerializedName("ids")
    List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
