package natanael.navigation.dagger;

import javax.inject.Singleton;

import dagger.Component;
import natanael.navigation.fragment.PropertyListFragment;

@Singleton
@Component(modules={ApplicationModule.class,RetrofitModule.class})
public interface RetrofitComponent
{
    void inject(PropertyListFragment fragment);
}