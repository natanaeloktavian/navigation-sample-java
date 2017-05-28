package natanael.navigation.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import natanael.navigation.model.Property;

public class RealmController
{
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application)
    {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment)
    {
        if (instance == null)
            instance = new RealmController(fragment.getActivity().getApplication());

        return instance;
    }

    public static RealmController with(Activity activity)
    {

        if (instance == null)
            instance = new RealmController(activity.getApplication());

        return instance;
    }

    public static RealmController with(Application application)
    {

        if (instance == null)
            instance = new RealmController(application);

        return instance;
    }

    public static RealmController getInstance()
    {
        return instance;
    }

    public Realm getRealm()
    {
        return realm;
    }

    public RealmResults<Property> getProperties()
    {
        return realm.where(Property.class).findAll();
    }

    public Property getProperty(String id)
    {
        return realm.where(Property.class).equalTo("title", id).findFirst();
    }

    public void insertProperties(ArrayList<Property> properties)
    {
        realm.beginTransaction();
        for(Property property : properties)
        {
            realm.insertOrUpdate(property);
        }
        realm.commitTransaction();
    }

    public RealmResults<Property> getPropertiesInCity(String city)
    {
        return realm.where(Property.class)
                .equalTo("city", city)
                .findAll();
    }

    public void setCity(ArrayList<Property> properties,String city)
    {
        realm.beginTransaction();
        for(Property property : properties)
            property.setCity(city);
        realm.commitTransaction();
    }
}