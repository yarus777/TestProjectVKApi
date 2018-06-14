package test.project.vkapi.core.db;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;
import test.project.vkapi.core.user.UserManager;

public class AppRepository implements IAppRepository {

    private final VkApi api;
    private final UserManager userManager;

    @Inject
    public AppRepository(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
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
    public Observable<UsersResponse> getUsers() {
        return api
                .getUsers(userManager.getToken(), "5.8", "photo_400_orig")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();
    }
}
