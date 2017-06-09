package natanael.navigation.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import natanael.navigation.dagger.ApplicationModule;
import natanael.navigation.dagger.DaggerRetrofitComponent;
import natanael.navigation.dagger.RetrofitComponent;
import natanael.navigation.dagger.RetrofitModule;

public class MyApplication extends Application
{
    private RetrofitComponent mRetrofitComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        initializeRealmConfiguration();
        initializeDagger();
    }

    private void initializeRealmConfiguration()
    {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initializeDagger()
    {
        mRetrofitComponent = DaggerRetrofitComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .retrofitModule(new RetrofitModule("https://api.nestoria.co.uk/"))
                .build();
    }

    public RetrofitComponent getRetrofitComponent()
    {
        return mRetrofitComponent;
    }
}