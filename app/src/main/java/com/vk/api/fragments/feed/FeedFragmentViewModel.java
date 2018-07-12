package com.vk.api.fragments.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import com.vk.api.fragments.BaseViewModel;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import test.project.vkapi.core.data.AppData;
import test.project.vkapi.core.feeds.models.Feed;

public class FeedFragmentViewModel extends BaseViewModel {

    private LiveData<List<Feed>> feeds;

    public FeedFragmentViewModel() {
        this.feeds = LiveDataReactiveStreams.fromPublisher(AppData.feed().getFeeds().toFlowable(BackpressureStrategy.LATEST));
    }

    public LiveData<List<Feed>> feeds() {
        return feeds;
    }
}