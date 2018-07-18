package com.vk.api.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.vk.api.fragments.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import test.project.vkapi.core.data.AppData;
import test.project.vkapi.core.user.models.User;

public class MainActivityViewModel extends BaseViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Boolean> isSignedIn = new MutableLiveData<>();

    public MainActivityViewModel() {
        AppData.auth().user()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                new Consumer<User>() {
                    @Override
                    public void accept(User u) throws Exception {
                        user.setValue(u);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //user.setValue(null);
                    }
                });

        AppData.auth().isSignedIn()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isLoggedIn) throws Exception {
                        isSignedIn.setValue(isLoggedIn);
                    }
                });
    }

    public void logout() {
        AppData.auth().logout();
    }

    public void login(String token) {
        AppData.auth().login(token);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<Boolean> isSignedIn() {
        return isSignedIn;
    }
}
