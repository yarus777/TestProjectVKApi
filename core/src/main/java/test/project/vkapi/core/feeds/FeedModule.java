package test.project.vkapi.core.feeds;

import org.modelmapper.ModelMapper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import test.project.vkapi.core.db.AppDatabase;
import test.project.vkapi.core.feeds.db.AttachmentsRepository;
import test.project.vkapi.core.feeds.db.LocalFeedRepository;
import test.project.vkapi.core.feeds.api.ApiFeedRepository;
import test.project.vkapi.core.feeds.db.PostSourceRepository;
import test.project.vkapi.core.feeds.db.models.AttachmentsDAO;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.PostSourceDAO;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.user.api.ApiUserRepository;

@Module
public class FeedModule {
    @Singleton
    @Provides
    FeedDAO provideDao(AppDatabase db) {
        return db.feedDAO();
    }

    @Singleton
    @Provides
    AttachmentsDAO provideAttachmentsDao(AppDatabase db) {
        return db.attachmentsDAO();
    }

    @Singleton
    @Provides
    PostSourceDAO providePostSourceDao(AppDatabase db) {
        return db.postSourceDAO();
    }

    @Provides
    @Singleton
    AttachmentsRepository provideAttachmentsRepository(AttachmentsDAO dao, ModelMapper mapper) {
        return new AttachmentsRepository(dao, mapper);
    }

    @Provides
    @Singleton
    PostSourceRepository providePostSourceRepository(PostSourceDAO dao, ModelMapper mapper) {
        return new PostSourceRepository(dao, mapper);
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
