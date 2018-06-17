package test.project.vkapi.core.feeds;

import java.util.List;

import test.project.vkapi.core.feeds.models.Feed;

public interface FeedStorage {
    void save(List<Feed> feeds);
}
