package test.project.vkapi.core.user;

import test.project.vkapi.core.user.models.User;

public interface UserStorage {
    void saveUser(User user);
}
