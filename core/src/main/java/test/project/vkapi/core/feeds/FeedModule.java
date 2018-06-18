package test.project.vkapi.core.feeds;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.db.AppDatabase;
import test.project.vkapi.core.feeds.db.LocalFeedRepository;
import test.project.vkapi.core.feeds.api.ApiFeedRepository;
import test.project.vkapi.core.feeds.db.models.FeedDAO;

@Module
public class FeedModule {
    @Singleton
    @Provides
    FeedDAO provideDao(AppDatabase db) {
        return db.feedDAO();
    }

    @Provides
    @Singleton
    @Named("DB")
    FeedRepository provideDbRepository(LocalFeedRepository feedRepository) {
        return feedRepository;
    }

    @Provides
    @Singleton
    @Named("API")
    FeedRepository provideApiRepository(ApiFeedRepository apiFeedRepository) {
        return apiFeedRepository;
    }

    @Provides
    @Singleton
    FeedStorage provideFeedStorage(LocalFeedRepository feedRepository) {
        return feedRepository;
    }
}
