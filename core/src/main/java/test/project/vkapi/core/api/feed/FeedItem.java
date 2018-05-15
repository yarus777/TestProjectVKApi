package test.project.vkapi.core.api.feed;

import com.google.gson.annotations.SerializedName;

public class FeedItem {
    @SerializedName("type")
    private String type;

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
