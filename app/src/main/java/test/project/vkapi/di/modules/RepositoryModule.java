package test.project.vkapi.di.modules;


import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.db.DBRepository;
import test.project.vkapi.core.db.RemoteRepository;
import test.project.vkapi.core.db.IAppRepository;

@Module
public abstract class RepositoryModule {

    @Binds
    public abstract IAppRepository bindAppRepository(RemoteRepository remoteRepository);


   /* @Provides
    @Singleton
    @Named("DB")
    IAppRepository provideDbRepository(DBRepository dbRepository) {
        return dbRepository;
    }

    @Provides
    @Singleton
    @Named("API")
    IAppRepository provideApiRepository(RemoteRepository remoteRepository) {
        return remoteRepository;
    }*/
}
