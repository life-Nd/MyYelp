package data;

import com.example.myyelp.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {


    @GET("businesses/search")
    Call<RestaurantResponse> getRestaurants(@Query("term") String name, @Query("location") String location);

//                                    @Query("name") String name,
//                                    @Query("phone") String phone,
//                                    @Query("rating") Double rating,
//                                    @Query("image_url") int image_url,
//                                    @Query("price") String price);
}

