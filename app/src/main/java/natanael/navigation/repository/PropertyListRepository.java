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
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PropertyListRepository implements IPropertyListRepository
{
    private Retrofit mRetrofit;

    public PropertyListRepository (Retrofit retrofit)
    {
        mRetrofit = retrofit;
    }

    @Override
    public void loadListings(final OnFinishedListener listener,String city)
    {
        //Without Dagger
        //ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        /*Call<ListingApiResponse> call = apiService.getPropertyListting("json","1","search_listings","uk","buy",city);
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
        });*/

        //Using Dagger
        ApiInterface apiService = mRetrofit.create(ApiInterface.class);

        //Using Rx Android
        Observable<ListingApiResponse> getContactRequest = apiService.getPropertyListting("json","1","search_listings","uk","buy",city);
        getContactRequest.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListingApiResponse>()
                {
                    @Override
                    public final void onCompleted()
                    {
                    }

                    @Override
                    public final void onError(Throwable e)
                    {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public final void onNext(ListingApiResponse response)
                    {
                        ListingResponse listingResponse = response.getResponse();

                        ArrayList<Property> properties = listingResponse.getListings();

                        listener.onDataLoaded(properties);
                    }
                });
    }
}