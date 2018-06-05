package test.project.vkapi.core.api.feed;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FeedItem extends BaseObservable {
    @SerializedName("type")
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("likes")
    private LikeItem likes;

    @SerializedName("attachments")
    private List<AttachmentItem> attachments;

    @SerializedName("comments")
    private CommentItem comments;

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

    public List<AttachmentItem> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentItem> attachments) {
        this.attachments = attachments;
    }
}
