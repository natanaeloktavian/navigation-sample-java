package natanael.navigation.repository;

import java.util.ArrayList;

import natanael.navigation.model.Property;

public interface IPropertyListRepository
{
    interface OnFinishedListener
    {
        void onDataLoaded(ArrayList<Property> items);
        void onFailure(String message);
    }

    void loadListings(OnFinishedListener listener,String city);
}