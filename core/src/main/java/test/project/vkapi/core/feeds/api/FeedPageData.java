package test.project.vkapi.core.feeds.api;

import java.util.List;

import test.project.vkapi.core.feeds.models.Feed;

public class FeedPageData {
    public List<Feed> feeds;

    public FeedPageData(List<Feed> feeds, String nextFrom) {
        this.feeds = feeds;
        this.nextFrom = nextFrom;
    }

    public String nextFrom;
}
