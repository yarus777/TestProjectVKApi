package test.project.vkapi.core.api.feed;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class FeedItem extends BaseObservable {

    public FeedItem() {
        photoAttachments = new ArrayList<>();
        audioAttachments = new ArrayList<>();
        videoAttachments = new ArrayList<>();
        linkAttachments = new ArrayList<>();
    }

    @SerializedName("type")
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("likes")
    private LikeItem likes;

    @SerializedName("comments")
    private CommentItem comments;

    private List<PhotoItem> photoAttachments;
    private List<AudioItem> audioAttachments;
    private List<VideoItem> videoAttachments;
    private List<LinkItem> linkAttachments;

    public List<PhotoItem> getPhotoAttachments() {
        return photoAttachments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LikeItem getLikes() {
        return likes;
    }

    public void setLikes(LikeItem likes) {
        this.likes = likes;
    }

    public CommentItem getComments() {
        return comments;
    }

    public void setComments(CommentItem comments) {
        this.comments = comments;
    }

    public void addPhotoAttachment(PhotoItem attachment) {
        photoAttachments.add(attachment);
    }

    public void addVideoAttachment(VideoItem attachment) {
        videoAttachments.add(attachment);
    }

    public void addAudioAttachment(AudioItem attachment) {
        audioAttachments.add(attachment);
    }

    public void addLinkAttachment(LinkItem attachment) {
        linkAttachments.add(attachment);
    }

    public List<AudioItem> getAudioAttachments() {
        return audioAttachments;
    }

    public List<VideoItem> getVideoAttachments() {
        return videoAttachments;
    }

    public List<LinkItem> getLinkAttachments() {
        return linkAttachments;
    }
}
