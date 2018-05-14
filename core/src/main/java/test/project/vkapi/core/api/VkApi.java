package test.project.vkapi.core.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.project.vkapi.core.api.feed.FeedResponse;

public interface VkApi {
    @GET("method/newsfeed.get")
    Call<FeedResponse> getFeed(@Query("access_token") String accessToken, @Query("v") String version);
}
