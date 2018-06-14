package test.project.vkapi.core.db;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.core.api.ApiCallback;
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

    public void getFeed(final ApiCallback<FeedResponse> callback) {

        api.getFeed(userManager.getToken(), "5.78", 100, "post").enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    onFailure(call, new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    public void getUsers(final ApiCallback<UsersResponse> callback) {
        api.getUsers(userManager.getToken(), "5.8", "photo_400_orig").enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    onFailure(call, new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

}
