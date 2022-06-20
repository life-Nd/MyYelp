package data;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestaurantModel {
    private String name;
    private Double rating;
    private List categories;
    private String categories_str;
    private String phone;
    private String address1;
    private String price;
    private String image_url;
    private Map location;
    private String display_address;

    public String getName() {
        return this.name;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public Double getRating() {
        return this.rating;
    }

    public String getCategories() {
        List categories_list = this.categories;
        categories_str ="";
        for (int i = 0; i < categories_list.size(); i++) {
            Map categories_map =
                    (Map) categories_list.get(i);

            categories_str = categories_str + " " + (String) categories_map.get("title");
        }

        return this.categories_str;


    }

    public String getPhone() {
        return this.phone;
    }


    public String getPrice() {
        return this.price;
    }

    public String getDisplayAddress() {
        List<String> address = (List) this.location.get("display_address");
        return address.toString().substring(1, address.toString().length() - 1);
    }


}


/*
{"businesses":

[{
"id":"IRIlwpomRvnXvpkeaGaM2A","alias":"saint-sushi-plateau-montréal",
"name":"Saint Sushi Plateau","image_url":"https://s3-media2.fl.yelpcdn.com/bphoto/CO5iGyNQVK-Q2SGbRFYsdA/o.jpg",
"is_closed":false,
"url":"https://www.yelp.com/biz/saint-sushi-plateau-montr%C3%A9al?adjust_creative=TBBpZz8L6rKaSR8Hx3mAHA&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=TBBpZz8L6rKaSR8Hx3mAHA",
"review_count":353,
"categories":[{"alias":"sushi","title":"Sushi Bars"},{"alias":"japanese","title":"Japanese"}],
"rating":4.5,
"coordinates":{"latitude":45.521106,"longitude":-73.575346},
"transactions":[],
"price":"$$",
"location":{"address1":"424 Avenue Duluth E","address2":"","address3":"","city":"Montreal","zip_code":"H2L 1A3","country":"CA","state":"QC","display_address":["424 Avenue Duluth E","Montreal, QC H2L 1A3","Canada"]},
"phone":"+15145077537",
"display_phone":"+1 514-507-7537",
"distance":981.9631711485946},

 */