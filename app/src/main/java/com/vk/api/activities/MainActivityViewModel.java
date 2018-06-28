package com.vk.api.activities;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vk.api.fragments.login.LoginListener;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.user.models.User;

public class MainActivityViewModel extends BaseObservable {

    private final UserManager userManager;
    private LoginListener listener;
    private User user;
    private final UserRepository userRepository;

    @Inject
    MainActivityViewModel(UserRepository userRepository, UserManager userManager) {
        this.userManager = userManager;
        this.userRepository = userRepository;
    }

    public void init() {
        if (!userManager.isAuthorized()) {
            listener.onNotAuthorized();
        } else {
            listener.onAuthorized();
            loadUser();
        }
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;

    }

    private void loadUser() {
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
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("MainViewModel", throwable.getMessage());
                            }
                        });
    }

    void logout() {
        userManager.logout();
        listener.onNotAuthorized();
    }

    void login(String token) {
        userManager.login(token);
        loadUser();
    }

    @BindingAdapter({"loadAvatar"})
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
