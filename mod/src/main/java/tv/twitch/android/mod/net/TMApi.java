package tv.twitch.android.mod.net;


import retrofit2.Call;
import retrofit2.http.GET;
import tv.twitch.android.mod.models.api.DonatorsResponse;

public interface TMApi {
    @GET("/pubapi/api/twitchmod/1.0/donators.json")
    Call<DonatorsResponse> getDonators();
}
