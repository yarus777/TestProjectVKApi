package test.project.vkapi.core.user.models;

public class User {

    private String firstName;

    private String lastName;

    private String photo;

    public User() {

    }

    public User(String firstName, String lastName, String photo ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
