package test.project.vkapi.core.db;

import java.util.List;

import test.project.vkapi.core.api.feed.FeedItem;

public interface FeedRepository {
    List<FeedItem> getItems();
}
