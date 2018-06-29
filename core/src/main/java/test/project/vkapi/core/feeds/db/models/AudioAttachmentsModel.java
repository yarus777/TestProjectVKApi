package test.project.vkapi.core.feeds.db.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "audios", foreignKeys = {@ForeignKey(entity = FeedDBModel.class, parentColumns = "id", childColumns = "feed_id")},
        indices = {@Index(value = {"feed_id"})})
public class AudioAttachmentsModel {

    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    private String artist;

    private String title;

    private int duration;

    private String url;

    @ColumnInfo(name = "feed_id")
    private String feedId;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
