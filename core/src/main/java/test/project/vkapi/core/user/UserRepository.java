package test.project.vkapi.core.user;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<User> getUser();
}
