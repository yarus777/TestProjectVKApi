package test.project.vkapi.core.feeds.models;

import java.util.List;

import test.project.vkapi.core.feeds.api.models.PostSource;

public class Feed {

    private String id;
    private String text;
    private int likesCount;
    private int commentsCount;
    private List<FeedAudioAttachment> audioAttachmentList;
    private List<FeedLinkAttachment> linkAttachmentList;
    private List<FeedPhotoAttachment> photoAttachmentList;
    private List<FeedVideoAttachment> videoAttachmentList;

    private PostSource source;

    public Feed() {

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostSource getSource() {
        return source;
    }

    public void setSource(PostSource source) {
        this.source = source;
    }

    public List<FeedAudioAttachment> getAudioAttachmentList() {
        return audioAttachmentList;
    }

    public void setAudioAttachmentList(List<FeedAudioAttachment> audioAttachmentList) {
        this.audioAttachmentList = audioAttachmentList;
    }

    public List<FeedLinkAttachment> getLinkAttachmentList() {
        return linkAttachmentList;
    }

    public void setLinkAttachmentList(List<FeedLinkAttachment> linkAttachmentList) {
        this.linkAttachmentList = linkAttachmentList;
    }

    public List<FeedPhotoAttachment> getPhotoAttachmentList() {
        return photoAttachmentList;
    }

    public void setPhotoAttachmentList(List<FeedPhotoAttachment> photoAttachmentList) {
        this.photoAttachmentList = photoAttachmentList;
    }

    public List<FeedVideoAttachment> getVideoAttachmentList() {
        return videoAttachmentList;
    }

    public void setVideoAttachmentList(List<FeedVideoAttachment> videoAttachmentList) {
        this.videoAttachmentList = videoAttachmentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
