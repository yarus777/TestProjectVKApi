package com.vk.api.di;

import com.vk.api.activities.MainActivity;
import com.vk.api.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        AppModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
