package test.project.vkapi.activities.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.BR;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.user.UserManager;


public class MainViewModel extends BaseObservable {

    private final VkApi api;
    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();

    @Inject
    public MainViewModel(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
    }

    public void setObserver(Observer observer) {
        observers.add(observer);
    }

    public void init() {
        notifyPropertyChanged(BR.loginStatus);
        if (!userManager.isAuthorized()) {
            if (observers != null) {
                for (Observer observer : observers) {
                    observer.onNotAuthorized();
                }
            }
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
        notifyPropertyChanged(BR.loginStatus);
    }

    public void login(String token) {
        userManager.login(token);
        notifyPropertyChanged(BR.loginStatus);
        loadFeed();

    }


    public interface Observer {
        void onNotAuthorized();
    }

    @Bindable
    public String getLoginStatus() {
        return userManager.isAuthorized() ? "signed in" : "signed out";
    }

}
