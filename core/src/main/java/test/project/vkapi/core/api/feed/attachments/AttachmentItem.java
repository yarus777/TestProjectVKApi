package test.project.vkapi.core.api.feed.attachments;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class AttachmentItem extends BaseObservable {
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
