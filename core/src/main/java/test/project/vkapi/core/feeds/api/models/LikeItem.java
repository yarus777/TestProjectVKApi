package test.project.vkapi.core.feeds.api.models;


import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class LikeItem extends BaseObservable {

    @SerializedName("count")
    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
