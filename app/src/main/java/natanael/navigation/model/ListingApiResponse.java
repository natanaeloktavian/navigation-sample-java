package natanael.navigation.model;

import com.google.gson.annotations.SerializedName;

public class ListingApiResponse
{
    @SerializedName("request")
    private ListingRequest request;

    @SerializedName("response")
    private ListingResponse response;

    public ListingRequest getRequest()
    {
        return request;
    }

    public void setRequest(ListingRequest request)
    {
        this.request = request;
    }

    public ListingResponse getResponse()
    {
        return response;
    }

    public void setResponse(ListingResponse response)
    {
        this.response = response;
    }
}