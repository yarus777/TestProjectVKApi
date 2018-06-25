package test.project.vkapi.core.feeds.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class FeedList {
    @SerializedName("items")
    private List<FeedItem> items;

    private HashMap<String, PostInfoSource> infoItems;

    public List<FeedItem> getItems() {
        return items;
    }

    public void setItems(List<FeedItem> items) {
        this.items = items;
    }

    public HashMap<String, PostInfoSource> getInfoItems() {
        return infoItems;
    }

    public void setInfoItems(HashMap<String, PostInfoSource> infoItems) {
        this.infoItems = infoItems;
    }
}