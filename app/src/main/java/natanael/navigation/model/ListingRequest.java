package natanael.navigation.model;

import com.google.gson.annotations.SerializedName;

public class ListingRequest
{
    @SerializedName("country")
    private String country;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}