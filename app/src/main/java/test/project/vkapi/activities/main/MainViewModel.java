package test.project.vkapi.activities.main;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.functions.Consumer;
import test.project.vkapi.BR;
import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.core.User;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.FeedList;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UserResponse;
import test.project.vkapi.core.db.IAppRepository;
import test.project.vkapi.core.user.UserManager;


public class MainViewModel extends BaseObservable {

    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();
    private FeedAdapter adapter;

    //public final ObservableList<FeedItem> feeds = new ObservableArrayList<>();
    //public final ItemBinding<FeedItem> itemBinding = ItemBinding.of(BR.feedItem, R.layout.feed_item);
    List<FeedItem> feeds = new ArrayList<>();
    List<UserResponse> users = new ArrayList<>();
    User user;

    private IAppRepository iAppRepository;

    @Inject
    public MainViewModel(IAppRepository vkRepository, UserManager userManager) {
        iAppRepository = vkRepository;
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
            loadData();
        }
    }

    void logout() {
        userManager.logout();
        notifyPropertyChanged(BR.loginStatus);
    }

    void login(String token) {
        userManager.login(token);
        notifyPropertyChanged(BR.loginStatus);
        loadData();
    }

    private void loadData() {
        iAppRepository.getFeed().subscribe(new Consumer<FeedResponse>() {
            @Override
            public void accept(FeedResponse feedResponse) throws Exception {
                FeedList feedList = feedResponse.getFeedList();
                feeds.addAll(feedList.getItems());
                if (feeds.size() > 0) {
                    adapter.setItems(feeds, feedList);
                }
            }
        });

        iAppRepository.getUsers().subscribe(new Consumer<User>() {
            @Override
            public void accept(User u) throws Exception {
                user = u;
                notifyPropertyChanged(BR.userName);
                notifyPropertyChanged(BR.imageUrl);
            }
        });



        /*iAppRepository.getUsers().subscribe(new Consumer<UsersResponse>() {
            @Override
            public void accept(UsersResponse usersResponse) throws Exception {
                for (UserResponse item : usersResponse.getResponse()) {
                    users.add(item);
                    notifyPropertyChanged(BR.userName);
                    notifyPropertyChanged(BR.imageUrl);
                }
            }
        });*/
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
        return user != null ? user.getPhoto() : "";
        //return users.size() > 0 ? users.get(0).getPhoto() : "";
    }

    @Bindable
    public String getUserName() {
        return user !=null ? user.getFirstName() + " " + user.getLastName() : "";
        //return users.size() > 0 ? users.get(0).getFirstName() + " " + users.get(0).getLastName() : "";
    }


}
