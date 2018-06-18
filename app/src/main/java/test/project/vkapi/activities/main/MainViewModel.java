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
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.BR;
import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.user.User;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.user.UserManager;


public class MainViewModel extends BaseObservable {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final UserManager userManager;
    private List<Observer> observers = new ArrayList<>();
    private FeedAdapter adapter;

    private User user;

    @Inject
    public MainViewModel(@Named("API") FeedRepository feedRepository, UserRepository userRepository, UserManager userManager) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
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
        feedRepository.getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Feed>>() {
                    @Override
                    public void accept(List<Feed> feeds) throws Exception {
                        if (feeds.size() > 0) {
                            adapter.setItems(feeds);
                        }
                    }
                });

        userRepository
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User u) throws Exception {
                        user = u;
                        notifyPropertyChanged(BR.userName);
                        notifyPropertyChanged(BR.imageUrl);
                    }
                });
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
    }

    @Bindable
    public String getUserName() {
        return user != null ? user.getFirstName() + " " + user.getLastName() : "";
    }


}
