package natanael.navigation.rest;

import java.util.ArrayList;

import natanael.navigation.model.ListingApiResponse;
import natanael.navigation.model.ListingResponse;
import natanael.navigation.model.Property;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface
{
    //https://api.nestoria.co.uk/api/?encoding=json&pretty=1&action=search_listings&country=uk&listing_type=buy&place_name=brighton
    /*@GET("api")
    Call<ListingApiResponse> getPropertyListting(@Query("encoding") String encoding,
                                                 @Query("pretty") String pretty,
                                                 @Query("action") String action,
                                                 @Query("country") String country,
                                                 @Query("listing_type") String listing_type,
                                                 @Query("place_name") String place_name);*/
    @GET("api")
    Observable<ListingApiResponse> getPropertyListting(@Query("encoding") String encoding,
                                                        @Query("pretty") String pretty,
                                                        @Query("action") String action,
                                                        @Query("country") String country,
                                                        @Query("listing_type") String listing_type,
                                                        @Query("place_name") String place_name);
}