package test.project.vkapi.core.data.modules;

import javax.inject.Inject;

import io.reactivex.Observable;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.user.models.User;

public class LoginModule {
    private final UserManager userManager;
    private final UserRepository userRepository;

    @Inject
    public LoginModule(UserManager userManager, UserRepository userRepository) {
        this.userManager = userManager;
        this.userRepository = userRepository;
    }

    public void login(String token) {
        userManager.login(token);
    }

    public Observable<User> user() {
        return userRepository.getUser();
    }

    public Observable<Boolean> isSignedIn() {
        return userManager.isSignedIn();
    }

    public void logout() {
        userManager.logout();
    }
}
