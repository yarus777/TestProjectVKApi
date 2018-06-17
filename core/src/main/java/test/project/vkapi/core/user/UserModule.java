package test.project.vkapi.core.user;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.db.AppDatabase;
import test.project.vkapi.core.user.api.ApiUserRepository;
import test.project.vkapi.core.user.db.LocalUserRepository;
import test.project.vkapi.core.user.db.models.UserDAO;

@Module
public class UserModule {
    @Singleton
    @Provides
    UserDAO provideDao(AppDatabase db) {
        return db.userDAO();
    }

    @Provides
    @Singleton
    UserStorage provideStorage(LocalUserRepository storage) {
        return storage;
    }

    @Provides
    @Singleton
    UserRepository provideApiRepository(ApiUserRepository userRepository) {
        return userRepository;
    }
}
