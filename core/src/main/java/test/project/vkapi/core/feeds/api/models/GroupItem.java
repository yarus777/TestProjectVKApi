package test.project.vkapi.core.feeds.api.models;

import com.google.gson.annotations.SerializedName;

public class GroupItem implements PostInfoSource{

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("photo_50")
    private String photo50;

    @SerializedName("photo_100")
    private String photo100;

    @SerializedName("photo_200")
    private String photo200;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "-" + id;
    }

    @Override
    public String getImgUrl() {
        return this.photo200;
    }

    @Override
    public String getUserName() {
        return this.name;
    }

}
