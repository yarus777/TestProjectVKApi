package test.project.vkapi.core.feeds;

import java.util.List;

import io.reactivex.Observable;
import test.project.vkapi.core.feeds.api.FeedPageData;
import test.project.vkapi.core.feeds.models.Feed;

public interface FeedRepository {
    Observable<FeedPageData> getFeed(String token, String from, int rows);
}
