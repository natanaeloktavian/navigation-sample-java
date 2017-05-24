package natanael.navigation.presenter;

import java.util.ArrayList;

import natanael.navigation.model.Property;
import natanael.navigation.repository.IPropertyListRepository;
import natanael.navigation.view.IPropertyListView;

public class PropertyListPresenter implements IPropertyListPresenter, IPropertyListRepository.OnFinishedListener
{
    private IPropertyListView contactListView;
    private IPropertyListRepository contactListRepository;
    private ArrayList<Property> items;

    public PropertyListPresenter(IPropertyListView mainView,IPropertyListRepository contactListRepository)
    {
        this.contactListView = mainView;
        this.contactListRepository = contactListRepository;
    }

    @Override
    public void loadListings(String city)
    {
        if(contactListRepository!=null)
            contactListRepository.loadListings(this,city);
    }

    @Override
    public void onItemClicked(int position)
    {
        if (contactListView != null && items!=null)
            contactListView.onItemClicked(items.get(position));
    }

    @Override public void onDestroy()
    {
        contactListView = null;
    }

    @Override
    public void onDataLoaded(ArrayList<Property> items)
    {
        this.items = items;
        if(contactListView!=null)
            contactListView.onDataLoaded(items);
    }

    @Override
    public void onFailure(String message)
    {
        if(contactListView!=null)
            contactListView.onFailure(message);
    }
}