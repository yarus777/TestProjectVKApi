package com.vk.api.fragments.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.vk.api.fragments.BaseViewModel;

import java.util.List;

import io.reactivex.functions.Consumer;
import test.project.vkapi.core.data.AppData;
import test.project.vkapi.core.feeds.models.Feed;

public class FeedFragmentViewModel extends BaseViewModel {

    private MutableLiveData<List<Feed>> feeds = new MutableLiveData<>();

    public FeedFragmentViewModel() {
        AppData.feed().getFeeds().subscribe(
                new Consumer<List<Feed>>() {
                    @Override
                    public void accept(List<Feed> feedsResponse) throws Exception {
                        feeds.setValue(feedsResponse);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        feeds.setValue(null);
                    }
                });
    }

    public LiveData<List<Feed>> feeds() {
        return feeds;
    }
}