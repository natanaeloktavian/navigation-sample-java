package natanael.navigation.presenter;

public interface IPropertyListPresenter
{
    void loadListings(String city);

    void onItemClicked(int position);

    void onDestroy();
}