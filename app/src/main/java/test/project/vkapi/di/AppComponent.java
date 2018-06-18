package test.project.vkapi.di;

import javax.inject.Singleton;

import dagger.Component;
import test.project.vkapi.activities.main.MainActivity;
import test.project.vkapi.core.user.UserModule;
import test.project.vkapi.di.modules.AppModule;
import test.project.vkapi.di.modules.DataModule;
import test.project.vkapi.di.modules.DatabaseModule;
import test.project.vkapi.core.feeds.FeedModule;
import test.project.vkapi.di.modules.RetrofitModule;

@Component(modules = {
        RetrofitModule.class,
        AppModule.class,
        DataModule.class,
        DatabaseModule.class,
        FeedModule.class,
        UserModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
