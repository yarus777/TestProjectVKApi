package test.project.vkapi.core.db;

import test.project.vkapi.core.api.ApiCallback;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;

public interface IAppRepository {
    void getFeed(ApiCallback<FeedResponse> callback);
    void getUsers(ApiCallback<UsersResponse> callback);
}
