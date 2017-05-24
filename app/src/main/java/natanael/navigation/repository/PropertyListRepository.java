package natanael.navigation.repository;

import java.util.ArrayList;

import natanael.navigation.model.ListingApiResponse;
import natanael.navigation.model.ListingResponse;
import natanael.navigation.model.Property;
import natanael.navigation.rest.ApiClient;
import natanael.navigation.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyListRepository implements IPropertyListRepository
{
    public PropertyListRepository ()
    {
    }

    @Override
    public void loadListings(final OnFinishedListener listener,String city)
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ListingApiResponse> call = apiService.getPropertyListting("json","1","search_listings","uk","buy",city);
        call.enqueue(new Callback<ListingApiResponse>()
        {
            @Override
            public void onResponse(Call<ListingApiResponse>call, Response<ListingApiResponse> response)
            {
                ListingApiResponse apiResponse = response.body();
                ListingResponse listingResponse = apiResponse.getResponse();

                ArrayList<Property> properties = listingResponse.getListings();
                listener.onDataLoaded(properties);
            }

            @Override
            public void onFailure(Call<ListingApiResponse>call, Throwable t)
            {
                listener.onFailure(t.getMessage());
            }
        });
    }
}