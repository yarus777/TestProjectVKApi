package test.project.vkapi.core.feeds;

import java.util.List;

import io.reactivex.Observable;
import test.project.vkapi.core.feeds.models.Feed;

public interface FeedRepository {
    Observable<List<Feed>> getFeed();
}
