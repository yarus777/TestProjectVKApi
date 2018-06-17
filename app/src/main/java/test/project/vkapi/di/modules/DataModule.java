package test.project.vkapi.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import org.modelmapper.ModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.storage.DataStorage;
import test.project.vkapi.core.storage.SharedPrefsDataStorage;

@Module
public class DataModule {
    private static final String PREFS_KEY = "preferences";
    private Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    DataStorage provideDataStorage() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        return new SharedPrefsDataStorage(prefs);
    }

    @Singleton
    @Provides
    ModelMapper provideMapper() {
        return new ModelMapper();
    }
}
