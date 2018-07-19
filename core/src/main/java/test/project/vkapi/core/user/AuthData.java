package test.project.vkapi.core.user;

import test.project.vkapi.core.user.models.User;

public class AuthData {
    public User user;
    public String token;

    public AuthData(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public AuthData() {

    }

    public boolean isAuthorized() {
        return user != null && token != null;
    }
}
