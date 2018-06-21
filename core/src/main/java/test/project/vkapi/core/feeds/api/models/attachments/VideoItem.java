package test.project.vkapi.core.feeds.api.models.attachments;

import com.google.gson.annotations.SerializedName;

public class VideoItem extends AttachmentItem {

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private int duration;

    @SerializedName("description")
    private String description;

    @SerializedName("access_key")
    private String access_key;

    @SerializedName("id")
    private String id;

    @SerializedName("owner_id")
    private String ownerId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
