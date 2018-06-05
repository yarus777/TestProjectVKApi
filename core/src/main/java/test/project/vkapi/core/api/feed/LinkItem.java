package test.project.vkapi.core.api.feed;

import com.google.gson.annotations.SerializedName;


public class LinkItem extends AttachmentItem {

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
