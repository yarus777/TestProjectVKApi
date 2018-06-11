package test.project.vkapi.activities.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.project.vkapi.BR;
import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UserResponse;
import test.project.vkapi.core.api.user.UsersResponse;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.databinding.NavHeaderMainBinding;


public class MainViewModel extends BaseObservable {

    private final VkApi api;
    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();
    private FeedAdapter adapter;

    //public final ObservableList<FeedItem> feeds = new ObservableArrayList<>();
    //public final ItemBinding<FeedItem> itemBinding = ItemBinding.of(BR.feedItem, R.layout.feed_item);
    List<FeedItem> feeds = new ArrayList<>();
    List<UserResponse> users = new ArrayList<>();

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
            loadUsers();
            loadFeed();
        }
    }

    private void loadFeed() {
        api.getFeed(userManager.getToken(), "5.78", 100, "post").enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                for (FeedItem item : response.body().getFeedList().getItems()) {
                    feeds.add(item);
                }
                if (feeds.size() > 0) {
                    adapter.setItems(feeds);
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
            }
        });

    }

    private void loadUsers() {
        api.getUsers(userManager.getToken(), "5.8", "photo_400_orig").enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    for (UserResponse item : response.body().getResponse()) {
                        users.add(item);
                        notifyPropertyChanged(BR.userName);
                        notifyPropertyChanged(BR.imageUrl);
                    }
                } else {
                    onFailure(call, new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }

    void logout() {
        userManager.logout();
        notifyPropertyChanged(BR.loginStatus);
    }

    void login(String token) {
        userManager.login(token);
        notifyPropertyChanged(BR.loginStatus);
        loadFeed();
        loadUsers();
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

    @BindingAdapter({"app:loadAvatar"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    @Bindable
    public String getImageUrl() {
        return users.size() > 0 ? users.get(0).getPhoto() : "";
    }

    @Bindable
    public String getUserName() {
        return users.size() > 0 ? users.get(0).getFirstName() + " " + users.get(0).getLastName() : "";
    }


}
