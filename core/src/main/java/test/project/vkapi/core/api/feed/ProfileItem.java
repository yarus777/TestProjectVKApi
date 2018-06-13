package test.project.vkapi.core.api.feed;

import com.google.gson.annotations.SerializedName;

public class ProfileItem implements PostInfoSource {

    @SerializedName("id")
    private String id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("photo_50")
    private String photo50;

    @SerializedName("photo_100")
    private String photo100;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getImgUrl() {
        return this.photo100;
    }

    @Override
    public String getPostUserText() {
        return this.firstName + " " + this.lastName;
    }
}
