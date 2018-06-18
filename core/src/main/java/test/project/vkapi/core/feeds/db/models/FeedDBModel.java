package test.project.vkapi.core.feeds.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "feeds", foreignKeys = {@ForeignKey(entity = AudioAttachmentsModel.class, parentColumns = "id", childColumns = "audio_id"),
                       @ForeignKey(entity = LinkAttachmentsModel.class, parentColumns = "id", childColumns = "link_id"),
                       @ForeignKey(entity = PhotoAttachmentsModel.class, parentColumns = "id", childColumns = "photo_id"),
                       @ForeignKey(entity = VideoAttachmentsModel.class, parentColumns = "id", childColumns = "video_id")})

public class FeedDBModel {

    @PrimaryKey
    private int id;

    private String sourceId;

    private String text;

    private int likesCount;

    private int commentsCount;

    @ColumnInfo(name = "audio_id")
    private int audioId;

    @ColumnInfo(name = "link_id")
    private int linkId;

    @ColumnInfo(name = "photo_id")
    private int photoId;

    @ColumnInfo(name = "video_id")
    private int videoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
