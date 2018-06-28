package test.project.vkapi.core.user;

import io.reactivex.Observable;
import test.project.vkapi.core.user.models.User;

public interface UserRepository {
    Observable<User> getUser();
}
