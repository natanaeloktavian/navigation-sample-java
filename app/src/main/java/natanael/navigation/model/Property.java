package natanael.navigation.model;

import com.google.gson.annotations.SerializedName;

public class Property
{
    @SerializedName("bathroom_number")
    private String bathroomNumber;

    @SerializedName("bedroom_number")
    private String bedroomNumber;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("thumb_url")
    private String thumbUrl;

    @SerializedName("price")
    private String price;

    @SerializedName("title")
    private String title;

    @SerializedName("summary")
    private String summary;

    public String getBathroomNumber()
    {
        return bathroomNumber;
    }

    public void setBathroomNumber(String bathroomNumber)
    {
        this.bathroomNumber = bathroomNumber;
    }

    public String getBedroomNumber()
    {
        return bedroomNumber;
    }

    public void setBedroomNumber(String bedroomNumber)
    {
        this.bedroomNumber = bedroomNumber;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getThumbUrl()
    {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl)
    {
        this.thumbUrl = thumbUrl;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }
}