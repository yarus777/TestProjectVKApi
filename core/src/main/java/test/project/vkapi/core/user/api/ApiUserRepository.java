package test.project.vkapi.core.user.api;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import test.project.vkapi.core.user.User;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.user.UserStorage;
import test.project.vkapi.core.user.api.models.UserResponse;
import test.project.vkapi.core.user.api.models.UsersResponse;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;

public class ApiUserRepository implements UserRepository {
    private final VkApi api;
    private final UserManager userManager;
    private final ModelMapper mapper;
    private final UserStorage userStorage;

    @Inject
    public ApiUserRepository(VkApi api, UserManager userManager, ModelMapper mapper, UserStorage userStorage) {
        this.api = api;
        this.userManager = userManager;
        this.mapper = mapper;
        this.userStorage = userStorage;
    }

    @Override
    public Observable<User> getUser() {
        return api
                .getUsers(userManager.getToken(), "5.80", "photo_400_orig")
                .map(new Function<UsersResponse, User>() {
                    @Override
                    public User apply(UsersResponse usersResponse) throws Exception {
                        User user = null;
                        if (usersResponse.getResponse().size() > 0) {
                            UserResponse userResponse = usersResponse.getResponse().get(0);
                            user = mapper.map(userResponse, User.class);
                        }
                        return user;
                    }
                })
                .toObservable()
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        userStorage.saveUser(user);
                    }
                });
    }
}
