package com.example.myyelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import data.RestaurantModel;

public class MainActivity extends AppCompatActivity implements RestaurantListAdapter.ItemClickListener {

    Spinner sortingSpinner;
    RestaurantListViewModel viewModel;
    private ArrayList<RestaurantModel> restaurantModelList;
    SearchView searchView;
    String selected;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RestaurantListAdapter restaurantListAdapter = new RestaurantListAdapter(this, restaurantModelList, this);
        recyclerView.setAdapter(restaurantListAdapter);


        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        viewModel.getRestaurantListObserver().observe(this, new Observer<RestaurantResponse>() {
            @Override
            public void onChanged(RestaurantResponse restaurantResponse) {
                if (restaurantResponse != null) {
                    restaurantModelList = restaurantResponse.restaurantModelList;
                    restaurantListAdapter.setRestaurantList(restaurantModelList);
                }
            }
        });


        viewModel.loadRestaurantList("sushi");

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.loadRestaurantList(query);
                restaurantListAdapter.clearRestaurantList();
                restaurantListAdapter.updateRestaurantList(restaurantModelList);
                searchView.clearFocus();
                searchView.setIconified(false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        sortingSpinner = (Spinner) findViewById(R.id.sorting_spinner);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.sorting_array, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//must
        sortingSpinner.setAdapter(dataAdapter);
        sortingSpinner.setSelection(dataAdapter.NO_SELECTION, true);
        sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapterView.getSelectedItem().toString();
                getIndex(selected);

                if (index == 0) {
                    Toast.makeText(MainActivity.this, "Clicked: Rating", Toast.LENGTH_SHORT).show();
                    restaurantListAdapter.sortByRating();
                    restaurantListAdapter.updateRestaurantList(restaurantModelList);
                }
                if (i == 1) {
                    restaurantListAdapter.sortByPrice();
                    restaurantListAdapter.updateRestaurantList(restaurantModelList);
                    Toast.makeText(MainActivity.this, "Clicked: Price", Toast.LENGTH_SHORT).show();
                }
                Log.d("❇️", "" + index + " " + selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

    }


    @Override
    public void onRestaurantClick(RestaurantModel restaurant) {
        Toast.makeText(this, "Clicked Movie Name is : " + restaurant.getName(), Toast.LENGTH_SHORT).show();
    }

    private void getIndex(String selected) {

        if (selected.equalsIgnoreCase("Rating")) {
            index = 0;
        } else if (selected.equalsIgnoreCase("Price")) {
            index = 1;
        }

    }
}
