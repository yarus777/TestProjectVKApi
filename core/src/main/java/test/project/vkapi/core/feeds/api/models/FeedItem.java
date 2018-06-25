package test.project.vkapi.core.feeds.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.feeds.api.models.attachments.AudioItem;
import test.project.vkapi.core.feeds.api.models.attachments.LinkItem;
import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.api.models.attachments.VideoItem;


public class FeedItem {

    public FeedItem() {
        photoAttachments = new ArrayList<>();
        audioAttachments = new ArrayList<>();
        videoAttachments = new ArrayList<>();
        linkAttachments = new ArrayList<>();
    }

    @SerializedName("post_id")
    private String postId;

    @SerializedName("source_id")
    private String sourceId;

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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId.replace("-", "");
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}