package test.project.vkapi.core.api.feed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedList {
    @SerializedName("items")
    private List<FeedItem> items;
}
