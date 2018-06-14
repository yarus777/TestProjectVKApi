package test.project.vkapi.core.api;


import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;

public interface VkApi {
    @GET("method/newsfeed.get")
    Single<FeedResponse> getFeed(@Query("access_token") String accessToken, @Query("v") String version, @Query("count") int count, @Query("filters") String filters);

    @GET("method/users.get")
    Single<UsersResponse> getUsers(@Query("access_token") String accessToken, @Query("v") String version, @Query("fields") String fields);
}
