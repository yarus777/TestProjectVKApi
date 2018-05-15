package test.project.vkapi.activities.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.activities.BaseViewModel;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.user.UserManager;

public class MainActivityViewModel extends BaseViewModel<MainActivity> {
    private final VkApi api;
    private final UserManager userManager;

    @Inject
    public MainActivityViewModel(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
    }

    public void logout() {
        userManager.logout();
        getView().updateLoginStatus(userManager.isAuthorized());
    }

    @Override
    public void setView(MainActivity view) {
        super.setView(view);
        getView().updateLoginStatus(userManager.isAuthorized());
        if (!userManager.isAuthorized()) {
            getView().startAuthActivity();
        } else {
            loadFeed();
        }
    }

    private void loadFeed() {
        api.getFeed(userManager.getToken(), "5.74").enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                List<String> texts = new ArrayList<>();
                for (FeedItem item : response.body().getFeedList().getItems()) {
                    texts.add(item.getType());
                }
                getView().setFeed("Loaded successfully");
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                getView().setError(t.getMessage());
            }
        });

    }

    public void login(String token) {
        userManager.login(token);
        getView().updateLoginStatus(userManager.isAuthorized());
        loadFeed();

    }
}
