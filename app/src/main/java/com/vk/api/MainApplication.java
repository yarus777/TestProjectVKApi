package com.vk.api;

import android.support.multidex.MultiDexApplication;

import com.vk.api.di.AppComponent;
import com.vk.api.di.DaggerAppComponent;
import com.vk.api.di.modules.AppModule;
import com.vk.api.di.modules.DataModule;
import com.vk.api.di.modules.DatabaseModule;

public class MainApplication extends MultiDexApplication implements AppInjector {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule(this))
                .dataModule(new DataModule(this))
                .build();
    }


    @Override
    public AppComponent getInjector() {
        return appComponent;
    }
}
