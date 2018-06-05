package test.project.vkapi.di;

import javax.inject.Singleton;

import dagger.Component;
import test.project.vkapi.activities.main.MainActivity;
import test.project.vkapi.di.modules.AppModule;
import test.project.vkapi.di.modules.DataModule;
import test.project.vkapi.di.modules.RetrofitModule;

@Component(modules = {
        RetrofitModule.class,
        AppModule.class,
        DataModule.class
        //, DatabaseModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
