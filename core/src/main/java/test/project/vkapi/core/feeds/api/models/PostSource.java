package test.project.vkapi.core.feeds.api.models;

public class PostSource {

    private String id;
    private String imgSource;

    private String userName;

    public PostSource() {

    }

    public PostSource(String id, String imgSource, String userName) {
        this.id = id;
        this.imgSource = imgSource;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return this.imgSource;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
