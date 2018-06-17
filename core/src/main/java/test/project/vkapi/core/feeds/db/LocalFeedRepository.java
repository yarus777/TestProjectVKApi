package test.project.vkapi.core.feeds.db;

import java.util.List;

import io.reactivex.Observable;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.FeedRepository;

public class LocalFeedRepository implements FeedRepository, FeedStorage {
    @Override
    public Observable<List<Feed>> getFeed() {
        return null;
    }

    @Override
    public void save(List<Feed> feeds) {

    }
}
