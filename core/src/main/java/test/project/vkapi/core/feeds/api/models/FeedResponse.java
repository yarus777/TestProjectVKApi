package test.project.vkapi.core.feeds.api.models;

import com.google.gson.annotations.SerializedName;

public class FeedResponse {
    @SerializedName("response")
    private FeedList feedList;

    public FeedList getFeedList() {
        return feedList;
    }

    public void setFeedList(FeedList feedList) {
        this.feedList = feedList;
    }
}
