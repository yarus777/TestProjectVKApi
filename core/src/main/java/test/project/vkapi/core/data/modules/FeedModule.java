package test.project.vkapi.core.data.modules;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.models.Feed;

public class FeedModule {
    private FeedRepository apiFeedRepository;

    @Inject
    public FeedModule(@Named("API") FeedRepository feedRepository) {
        apiFeedRepository = feedRepository;
    }

    public Observable<List<Feed>> getFeeds() {
        return apiFeedRepository.getFeed();
    }
}
