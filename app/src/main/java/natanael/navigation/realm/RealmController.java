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
    private static RealmController mInstance;
    private final Realm mRealm;

    public RealmController(Application application)
    {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment)
    {
        if (mInstance == null)
            mInstance = new RealmController(fragment.getActivity().getApplication());

        return mInstance;
    }

    public static RealmController with(Activity activity)
    {
        if (mInstance == null)
            mInstance = new RealmController(activity.getApplication());

        return mInstance;
    }

    public static RealmController with(Application application)
    {
        if (mInstance == null)
            mInstance = new RealmController(application);

        return mInstance;
    }

    public static RealmController getInstance()
    {
        return mInstance;
    }

    public Realm getRealm()
    {
        return mRealm;
    }

    public RealmResults<Property> getProperties()
    {
        return mRealm.where(Property.class).findAll();
    }

    public Property getProperty(String id)
    {
        return mRealm.where(Property.class).equalTo("title", id).findFirst();
    }

    public void insertProperties(ArrayList<Property> properties)
    {
        mRealm.beginTransaction();
        for(Property property : properties)
        {
            mRealm.insertOrUpdate(property);
        }
        mRealm.commitTransaction();
    }

    public RealmResults<Property> getPropertiesInCity(String city)
    {
        return mRealm.where(Property.class)
                .equalTo("city", city)
                .findAll();
    }

    public void setCity(ArrayList<Property> properties,String city)
    {
        mRealm.beginTransaction();
        for(Property property : properties)
            property.setCity(city);
        mRealm.commitTransaction();
    }
}