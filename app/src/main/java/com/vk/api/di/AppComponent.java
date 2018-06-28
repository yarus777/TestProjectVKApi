package com.vk.api.di;

import com.vk.api.activities.MainActivity;
import com.vk.api.di.modules.AppModule;
import com.vk.api.di.modules.DataModule;
import com.vk.api.di.modules.DatabaseModule;
import com.vk.api.di.modules.RetrofitModule;
import com.vk.api.fragments.feed.FeedFragment;

import javax.inject.Singleton;

import dagger.Component;
import test.project.vkapi.core.feeds.FeedModule;
import test.project.vkapi.core.user.UserModule;

@Component(modules = {
        AppModule.class,
        DataModule.class,
        DatabaseModule.class,
        RetrofitModule.class,
        UserModule.class,
        FeedModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(FeedFragment fragment);
}
