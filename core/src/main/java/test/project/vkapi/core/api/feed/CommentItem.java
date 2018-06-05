package test.project.vkapi.core.api.feed;


import android.arch.persistence.room.Entity;
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
