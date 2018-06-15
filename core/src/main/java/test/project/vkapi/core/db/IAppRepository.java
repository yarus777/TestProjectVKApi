package test.project.vkapi.core.db;

import java.util.List;

import io.reactivex.Observable;
import test.project.vkapi.core.User;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;

public interface IAppRepository {
    Observable<FeedResponse> getFeed();
    Observable<User> getUsers();
}
