package test.project.vkapi.core.feeds.models;

import test.project.vkapi.core.feeds.api.models.PostInfoSource;

public class Feed {

    private String text;
    private int likesCount;
    private int commentsCount;

    private PostInfoSource source;

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

    public PostInfoSource getSource() {
        return source;
    }

    public void setSource(PostInfoSource source) {
        this.source = source;
    }
}
