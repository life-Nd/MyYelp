package com.example.myyelp;


import java.util.Comparator;

import data.RestaurantModel;

public class RatingSorter implements Comparator<RestaurantModel> {

    @Override
    public int compare(RestaurantModel res1, RestaurantModel res2) {
        Double rating1 = res1.getRating() != null ? res1.getRating() : 0.0;
        Double rating2 = res2.getPrice() != null ? res2.getRating() : 0.0;

        return Double.compare(rating1, rating2);
    }
}
