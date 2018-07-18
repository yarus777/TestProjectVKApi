package test.project.vkapi.core.user.api;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.core.user.models.User;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.user.UserStorage;
import test.project.vkapi.core.user.api.models.UserResponse;
import test.project.vkapi.core.user.api.models.UsersResponse;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;

public class ApiUserRepository implements UserRepository {
    private final VkApi api;
    private final ModelMapper mapper;
    private final UserStorage userStorage;

    private Subject<User> user = PublishSubject.create();

    @Inject
    public ApiUserRepository(VkApi api, UserManager userManager, ModelMapper mapper, UserStorage userStorage) {
        this.api = api;
        this.mapper = mapper;
        this.userStorage = userStorage;
        userManager.getToken().subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String token) throws Exception {
                        if (token != null && !token.isEmpty()) {
                            loadUser(token);
                        }
                    }
                }
        );
    }

    private void loadUser(String token) {
        api
                .getUsers(token, "5.80", "photo_400_orig")
                .enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        if (response.isSuccessful() && response.body().getResponse().size() > 0) {
                            UserResponse userResponse = response.body().getResponse().get(0);
                            User user = mapper.map(userResponse, User.class);
                            userStorage.saveUser(user);
                            ApiUserRepository.this.user.onNext(user);
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public Observable<User> getUser() {
        return user;
    }
}
