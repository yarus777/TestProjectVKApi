package test.project.vkapi.core.api.feed;

import com.google.gson.annotations.SerializedName;

public class FeedItem {
    @SerializedName("source_id")
    private String sourceId;

    @SerializedName("text")
    private String text;
}
