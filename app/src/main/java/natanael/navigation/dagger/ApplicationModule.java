package natanael.navigation.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule
{
    Application mApplication;

    public ApplicationModule(Application mApplication)
    {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication()
    {
        return mApplication;
    }
}