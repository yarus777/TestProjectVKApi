package test.project.vkapi.core.user;

import android.webkit.CookieManager;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.storage.DataStorage;
import test.project.vkapi.core.user.api.models.UserResponse;
import test.project.vkapi.core.user.api.models.UsersResponse;
import test.project.vkapi.core.user.models.User;

@Singleton
public class UserManager {
    private static final String TOKEN = "token";
    private final VkApi api;
    private final ModelMapper mapper;
    private final UserStorage userStorage;

    private Subject<String> token = BehaviorSubject.create();

    @Inject
    public UserManager(final DataStorage dataStorage, VkApi api, ModelMapper mapper, UserStorage userStorage) {
        this.api = api;
        this.mapper = mapper;
        this.userStorage = userStorage;
        token.subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        dataStorage.save(TOKEN, s);
                    }
                }
        );
        String t = dataStorage.get(TOKEN);
        if(t != null) {
            token.onNext(t);
        } else {
            token.onNext("");
        }
    }

    public Observable<String> getToken() {
        return token;
    }

    public Single<AuthData> login(final String token) {
        return api
                .getUsers(token, "5.80", "photo_400_orig")
                .map(new Function<UsersResponse, User>() {
                    @Override
                    public User apply(UsersResponse usersResponse) throws Exception {
                        UserResponse userResponse = usersResponse.getResponse().get(0);
                        return mapper.map(userResponse, User.class);
                    }
                })
                .doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        userStorage.saveUser(user);
                    }
                })
                .map(new Function<User, AuthData>() {
                    @Override
                    public AuthData apply(User user) throws Exception {
                        return new AuthData(user, token);
                    }
                });
    }

    public void logout() {
        token.onNext(null);
        CookieManager.getInstance().removeAllCookies(null);
    }
}
