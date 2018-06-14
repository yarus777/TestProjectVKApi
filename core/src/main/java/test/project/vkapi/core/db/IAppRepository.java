package test.project.vkapi.core.db;

import io.reactivex.Observable;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;

public interface IAppRepository {
    Observable<FeedResponse> getFeed();
    Observable<UsersResponse> getUsers();
}
