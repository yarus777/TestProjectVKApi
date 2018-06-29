package com.vk.api.fragments.feed;


import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vk.api.adapters.FeedAdapter;
import com.vk.api.fragments.BaseViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.models.Feed;


public class FeedFragmentViewModel extends BaseViewModel {

    private FeedAdapter adapter;
    private final FeedRepository feedRepository;

    @Inject
    FeedFragmentViewModel(@Named("API") FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
        adapter = new FeedAdapter();
        loadFeeds();
    }

    public void setListener(FeedItemClickListener listener){
        adapter.setListener(listener);
    }

    private void loadFeeds() {
        feedRepository.getFeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Feed>>() {
                               @Override
                               public void accept(List<Feed> feeds) throws Exception {
                                   if (feeds != null && feeds.size() > 0) {
                                       adapter.setItems(feeds);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("MainViewModel", throwable.getMessage());
                            }
                        });

    }


    @Bindable
    public FeedAdapter getAdapter() {
        return adapter;
    }

    @BindingAdapter({"adapter"})
    public static void bind(RecyclerView recyclerView, FeedAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

}
