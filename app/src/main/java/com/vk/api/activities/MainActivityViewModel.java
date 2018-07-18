package com.vk.api.activities;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vk.api.fragments.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.core.data.AppData;
import test.project.vkapi.core.user.UserManager;
import test.project.vkapi.core.user.UserRepository;
import test.project.vkapi.core.user.models.User;

public class MainActivityViewModel extends BaseViewModel {


    private MutableLiveData<String> userName;
    private MutableLiveData<String> userpicUrl;


    MainActivityViewModel() {
        AppData.auth().user().subscribe(
                new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        userName.setValue(user.getFirstName());
                        userpicUrl.setValue(user.getPhoto());
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        userName.setValue(null);
                        userpicUrl.setValue(null);
                    }
                });
    }

    public void logout() {
        AppData.auth().logout();
    }

    private void login(String token) {
        AppData.auth().login(token);
    }

    public LiveData<String> getImageUrl() {
        return userpicUrl;
    }

    public LiveData<String> getUserName() {
        return userName;
    }
}
