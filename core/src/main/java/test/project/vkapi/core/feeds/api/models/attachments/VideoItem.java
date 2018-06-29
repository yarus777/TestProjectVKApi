package test.project.vkapi.core.feeds.api.models.attachments;

import com.google.gson.annotations.SerializedName;

public class VideoItem {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private int duration;

    @SerializedName("description")
    private String description;

    @SerializedName("access_key")
    private String accessKey;


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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
