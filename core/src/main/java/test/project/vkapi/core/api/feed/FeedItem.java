package test.project.vkapi.core.api.feed;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class FeedItem extends BaseObservable {
    @SerializedName("type")
    private String type;

    @PrimaryKey
    @NonNull
    @SerializedName("source_id")
    private String feedId;

    @SerializedName("text")
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
