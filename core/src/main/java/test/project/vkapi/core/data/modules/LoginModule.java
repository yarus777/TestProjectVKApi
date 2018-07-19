package test.project.vkapi.core.data.modules;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import test.project.vkapi.core.user.AuthData;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.user.models.User;

public class LoginModule {
    private final UserManager userManager;

    @Inject
    public LoginModule(UserManager userManager) {
        this.userManager = userManager;
    }

    public Observable<String> getToken() {
        return userManager.getToken();
    }

    public Single<AuthData> login(String token) {
        return userManager.login(token);
    }

    public void logout() {
        userManager.logout();
    }

    public UserManager manager() {
        return userManager;
    }
}
