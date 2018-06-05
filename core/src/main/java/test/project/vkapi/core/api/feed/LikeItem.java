package test.project.vkapi.core.api.feed;


import android.arch.persistence.room.Entity;
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
