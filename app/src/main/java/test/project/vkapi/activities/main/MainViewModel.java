package test.project.vkapi.activities.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.BR;
import test.project.vkapi.FeedAdapter;
import test.project.vkapi.R;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.user.UserManager;


public class MainViewModel extends BaseObservable {

    private final VkApi api;
    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();
    private FeedAdapter adapter;

    //public final ObservableList<FeedItem> feeds = new ObservableArrayList<>();
    //public final ItemBinding<FeedItem> itemBinding = ItemBinding.of(BR.feedItem, R.layout.feed_item);
    List<FeedItem> feeds = new ArrayList<>();

    @Inject
    public MainViewModel(VkApi api, UserManager userManager) {
        this.api = api;
        this.userManager = userManager;
        adapter = new FeedAdapter();
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
        api.getFeed(userManager.getToken(), "5.78").enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {

                for (FeedItem item : response.body().getFeedList().getItems()) {
                    feeds.add(item);
                }
                adapter.setItems(feeds);
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

    @Bindable
    public FeedAdapter getAdapter() {
      return adapter;
    }

    @BindingAdapter({"app:adapter"})
    public static void bind(RecyclerView recyclerView, FeedAdapter adapter) {
        recyclerView.setAdapter(adapter);

    }

}
