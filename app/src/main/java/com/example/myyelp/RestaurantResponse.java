package com.example.myyelp;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import data.RestaurantModel;

public class RestaurantResponse {

    @SerializedName("businesses")
    public ArrayList<RestaurantModel> restaurantModelList;

}
