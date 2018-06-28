package test.project.vkapi.core.feeds.api.models;

import com.google.gson.annotations.SerializedName;

import test.project.vkapi.core.api.error.BaseResponse;

public class FeedResponse extends BaseResponse {
    @SerializedName("response")
    private FeedList feedList;

    public FeedList getFeedList() {
        return feedList;
    }

    public void setFeedList(FeedList feedList) {
        this.feedList = feedList;
    }
}
