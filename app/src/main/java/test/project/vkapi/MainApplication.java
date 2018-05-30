package test.project.vkapi;

import android.support.multidex.MultiDexApplication;

import test.project.vkapi.di.AppComponent;
import test.project.vkapi.di.DaggerAppComponent;
import test.project.vkapi.di.modules.AppModule;
import test.project.vkapi.di.modules.DataModule;
import test.project.vkapi.di.modules.DatabaseModule;

public class MainApplication extends MultiDexApplication implements AppInjector {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();
    }


    @Override
    public AppComponent getInjector() {
        return appComponent;
    }
}
