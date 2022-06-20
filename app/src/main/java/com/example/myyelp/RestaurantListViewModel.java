package com.example.myyelp;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import data.YelpAPI;
import data.YelpClient;

import data.RestaurantModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurantListViewModel extends ViewModel {

    private MutableLiveData<RestaurantResponse> restaurantLiveData;
    RestaurantResponse recyclerDataList;

    public RestaurantListViewModel() {
        restaurantLiveData = new MutableLiveData<>();

    }


    public MutableLiveData<RestaurantResponse> getRestaurantListObserver() {
        return restaurantLiveData;

    }

    public void loadRestaurantList(String searchText) {
        Handler handler = new Handler();

        handler.postDelayed(() -> {
            YelpAPI api = new YelpClient().build();
            Call<RestaurantResponse> call = api.getRestaurants(searchText, "montreal");
            Callback<RestaurantResponse> callback = new Callback<RestaurantResponse>() {

                @Override
                public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                    recyclerDataList = response.body();
                    restaurantLiveData.postValue(recyclerDataList);
                    Log.d("✅ body()", "response =" + response.body().toString());
                    for (int x = 0; x < recyclerDataList.restaurantModelList.size(); x++) {
                        final RestaurantModel restaurantModel = recyclerDataList.restaurantModelList.get(x);
                        Log.d("✅", " NAME: " + restaurantModel.getName());

                    }

                }

                @Override
                public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                    Log.d("❌ Error Test", "error" + t.getLocalizedMessage());

                }
            };

            call.enqueue(callback);
        }, 1000);
    }
}

