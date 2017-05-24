package natanael.navigation.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListingResponse
{
    @SerializedName("listings")
    private ArrayList<Property> listings;

    public ArrayList<Property> getListings()
    {
        return listings;
    }

    public void setListings(ArrayList<Property> listings)
    {
        this.listings = listings;
    }
}