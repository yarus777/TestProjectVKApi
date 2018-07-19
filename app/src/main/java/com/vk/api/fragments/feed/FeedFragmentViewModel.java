package com.vk.api.fragments.feed;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.vk.api.fragments.BaseViewModel;

import java.util.concurrent.Executors;

import test.project.vkapi.core.data.AppData;
import test.project.vkapi.core.feeds.api.DataSourceFactory;
import test.project.vkapi.core.feeds.api.FeedDataSource;
import test.project.vkapi.core.feeds.models.Feed;

public class FeedFragmentViewModel extends BaseViewModel {
    public static final int ROWS_PER_PAGE = 10;
    private PagedList<Feed> feeds;
    //private DataSourceFactory sourceFactory;

    public FeedFragmentViewModel() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ROWS_PER_PAGE)
                .setInitialLoadSizeHint(ROWS_PER_PAGE)
                .setEnablePlaceholders(false)
                .build();

        /*sourceFactory = new DataSourceFactory(
                AppData.feed().repository(),
                AppData.auth().manager()
        );
        feeds = new LivePagedListBuilder<>(sourceFactory, config).build();*/

        feeds = new PagedList.Builder<>(
                new FeedDataSource(AppData.feed().repository(), AppData.auth().manager()),
                config)
                .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    public PagedList<Feed> getFeeds() {
        return feeds;
    }
}