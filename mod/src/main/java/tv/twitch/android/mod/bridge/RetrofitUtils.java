package tv.twitch.android.mod.bridge;


import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    public static Retrofit getRetrofitClient(String baseUrl) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
}
