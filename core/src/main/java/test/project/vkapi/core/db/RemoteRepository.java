package test.project.vkapi.core.db;

import org.modelmapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.core.User;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UserResponse;
import test.project.vkapi.core.api.user.UsersResponse;
import test.project.vkapi.core.user.UserManager;

public class RemoteRepository implements IAppRepository {

    private final VkApi api;
    private final UserManager userManager;
    ModelMapper mapper;

    //private @Named("DB")
    //IAppRepository dbRepository;

    @Inject
    public RemoteRepository(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
        mapper = new ModelMapper();
    }

    @Override
    public Observable<FeedResponse> getFeed() {
        return api
                .getFeed(userManager.getToken(), "5.78", 100, "post")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();
    }

    @Override
    public Observable<User> getUsers() {
        return api.getUsers(userManager.getToken(), "5.8", "photo_400_orig").map(new Function<UsersResponse, User>() {
            @Override
            public User apply(UsersResponse usersResponse) throws Exception {
                User user = null;
                if (usersResponse.getResponse().size() > 0) {
                    UserResponse userResponse = usersResponse.getResponse().get(0);
                    user = mapper.map(userResponse, User.class);
                    //user = new User(userResponse.getFirstName(), userResponse.getLastName(), userResponse.getPhoto());
                }
                return user;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toObservable();

    }

        /*return api
                .getUsers(userManager.getToken(), "5.8", "photo_400_orig")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();*/
}
