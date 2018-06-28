package test.project.vkapi.core.feeds.api.models;


import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class CommentItem extends BaseObservable {

    @SerializedName("count")
    private int commentCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
