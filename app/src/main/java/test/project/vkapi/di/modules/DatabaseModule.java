package test.project.vkapi.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*@Module
public class DatabaseModule {

    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public FeedDatabase provideDatabase() {
        return Room.databaseBuilder(context, FeedDatabase.class, "database").build();
    }
}*/
