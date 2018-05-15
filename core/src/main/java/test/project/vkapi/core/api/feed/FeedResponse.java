package test.project.vkapi.core.api.feed;

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
