package test.project.vkapi.core.feeds.db.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "videos", foreignKeys = {@ForeignKey(entity = FeedDBModel.class, parentColumns = "id", childColumns = "feed_id")},
        indices = {@Index(value = {"feed_id"})})
public class VideoAttachmentsModel {

    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    private String title;

    private int duration;

    private String description;

    private String accessKey;

    @ColumnInfo(name = "feed_id")
    private String feedId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
