package test.project.vkapi.core.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.project.vkapi.core.feeds.api.models.FeedResponse;
import test.project.vkapi.core.user.api.models.UsersResponse;

public interface VkApi {
    @GET("method/newsfeed.get")
    Single<FeedResponse> getFeed(
            @Query("access_token") String accessToken,
            @Query("v") String version,
            @Query("start_from") String startFrom,
            @Query("count") int count,
            @Query("filters") String filters);

    @GET("method/users.get")
    Single<UsersResponse> getUsers(@Query("access_token") String accessToken, @Query("v") String version, @Query("fields") String fields);

}
