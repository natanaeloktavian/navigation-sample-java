package natanael.navigation.view;

import java.util.ArrayList;

import natanael.navigation.model.Property;

public interface IPropertyListView
{
    void onDataLoaded(ArrayList<Property> properties);

    void onItemClicked(Property property);

    void onFailure(String message);
}