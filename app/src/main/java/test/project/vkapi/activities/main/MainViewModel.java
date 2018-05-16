package test.project.vkapi.activities.main;

import android.content.Context;
import android.databinding.BaseObservable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.user.UserManager;


public class MainViewModel extends BaseObservable {

    private Context context;
    private final VkApi api;
    private final UserManager userManager;

    @Inject
    public MainViewModel(Context context, VkApi api, UserManager userManager) {
        this.context = context;
        this.api = api;
        this.userManager = userManager;
    }

    public void init() {
        //updateLoginStatus(userManager.isAuthorized());
        if (!userManager.isAuthorized()) {
            //startAuthActivity();
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
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
            }
        });

    }

    public void logout() {
        userManager.logout();
        //updateLoginStatus(userManager.isAuthorized());
    }

    public void login(String token) {
        userManager.login(token);
        //updateLoginStatus(userManager.isAuthorized());
        loadFeed();

    }

   /*public void updateLoginStatus(boolean isLoggedIn) {
        status.setText(isLoggedIn ? "signed in" : "signed out");
    }*/
}
