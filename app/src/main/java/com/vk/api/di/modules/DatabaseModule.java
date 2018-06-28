package com.vk.api.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.db.AppDatabase;

@Module
public class DatabaseModule {

    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, "database").build();
    }
}
