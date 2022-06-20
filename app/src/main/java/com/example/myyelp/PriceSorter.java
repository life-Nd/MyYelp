package com.example.myyelp;

import java.util.Comparator;

import data.RestaurantModel;

public class PriceSorter implements Comparator<RestaurantModel> {
    @Override
    public int compare(RestaurantModel res1, RestaurantModel res2) {
        int price1 = res1.getPrice() != null ? res1.getPrice().length() : 0;
        int price2 = res2.getPrice() != null ? res2.getPrice().length() : 0;

        return Integer.compare(price1, price2);
    }
}
