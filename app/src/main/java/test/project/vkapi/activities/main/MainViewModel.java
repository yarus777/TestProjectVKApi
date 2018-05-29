package test.project.vkapi.activities.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
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


public class MainViewModel extends ViewModel {

    private final VkApi api;
    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();

    private MutableLiveData<String> loginStatus = new MutableLiveData<>();

    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    private void updateLoginStatus() {
        loginStatus.setValue(userManager.isAuthorized() ? "signed in" : "signed out");
    }


    public MainViewModel(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
    }

    public void setObserver(Observer observer) {
        observers.add(observer);
    }

    public void init() {
        updateLoginStatus();
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
        updateLoginStatus();
    }

    public void login(String token) {
        userManager.login(token);
        updateLoginStatus();
        loadFeed();
    }


    public interface Observer {
        void onNotAuthorized();
    }


}
