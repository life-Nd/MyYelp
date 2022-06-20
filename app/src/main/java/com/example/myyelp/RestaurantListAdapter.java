package com.example.myyelp;

import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data.RestaurantModel;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RestaurantModel> restaurantList;
    private ItemClickListener clickListener;


    public RestaurantListAdapter(Context context, ArrayList<RestaurantModel> restaurantList, ItemClickListener clickListener) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.clickListener = clickListener;
    }

    public void setRestaurantList(ArrayList<RestaurantModel> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, int position) {

        RestaurantModel restaurantModel = this.restaurantList.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Glide.with(context).load(this.restaurantList.get(position).getImageUrl()).apply(RequestOptions.centerInsideTransform()).into(holder.image);
        int index = position + 1;
        viewHolder.index.setText("" + index);
        viewHolder.name.setText(restaurantModel.getName());

        Float rating =
                restaurantModel.getRating() != null ? restaurantModel.getRating().floatValue() : Float.valueOf("0.0");
        viewHolder.rating.setRating(rating);
        viewHolder.price.setText(restaurantModel.getPrice() != null ? restaurantModel.getPrice() : "");
        viewHolder.category.setText(restaurantModel.getCategories());
        viewHolder.address.setText(restaurantModel.getDisplayAddress());
        viewHolder.phone.setText("" + restaurantModel.getPhone());

    }

    @Override
    public int getItemCount() {
        if (this.restaurantList != null) {
            return this.restaurantList.size();

        }

        return 0;
    }

    public ArrayList<RestaurantModel> sortByRating() {
        if (this.restaurantList != null)
            Collections.sort(this.restaurantList, new RatingSorter().reversed());
        return this.restaurantList;

    }

    public List<RestaurantModel> sortByPrice() {
        if (this.restaurantList != null)
            Collections.sort(this.restaurantList, new PriceSorter());
        return this.restaurantList;
    }

    public void clearRestaurantList() {
        this.restaurantList.clear();
    }

    public void updateRestaurantList(final List<RestaurantModel> restaurantModelArrayList) {
        this.restaurantList = (ArrayList<RestaurantModel>) restaurantModelArrayList;
        notifyDataSetChanged();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView index;
        RatingBar rating;
        TextView price;
        TextView category;
        TextView phone;
        TextView address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            index = (TextView) itemView.findViewById(R.id.index);
            rating = (RatingBar) itemView.findViewById(R.id.rating_bar);
            price = (TextView) itemView.findViewById(R.id.price);
            category = (TextView) itemView.findViewById(R.id.category);
            address = (TextView) itemView.findViewById(R.id.address);
            phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }

    public interface ItemClickListener {
        public void onRestaurantClick(RestaurantModel restaurant);
    }
}
