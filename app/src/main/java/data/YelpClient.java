package data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://api.yelp.com/v3/businesses/search?term=delis&latitude=37.786882&longitude=-122.399972


import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class YelpClient {

    private static String YELP_API_KEY = "vDa4CVUdlrdnJX29Ghww6Tud8IIT0IOjAPJV1ew94i_wnaeBuNhGoxCJH2cW4qaElWH58N8_f94106RziE1EbjkC3AvexMvtZximM7OBS4TFOmOqQcFDBI2VCt-oYnYx";

    public YelpAPI build() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {

                        return chain.proceed(chain
                                .request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer "+ YELP_API_KEY)
                                .build());
                    }
                }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(YelpAPI.class);
    }
}