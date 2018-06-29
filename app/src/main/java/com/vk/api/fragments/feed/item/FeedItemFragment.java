package com.vk.api.fragments.feed.item;

import com.android.databinding.library.baseAdapters.BR;
import com.vk.api.R;
import com.vk.api.di.AppComponent;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.BaseViewModel;
import com.vk.api.fragments.feed.FeedFragment;
import com.vk.api.views.feed.FeedItemViewModel;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedItemFragment extends BaseFragment {
    public static final String TAG = FeedItemFragment.class.getSimpleName();

    private Feed feedItem;

    public static FeedItemFragment newInstance() {
        FeedItemFragment fragment = new FeedItemFragment();
        return fragment;
    }

    @Override
    protected void inject(AppComponent injector) {

    }

    @Override
    public int getBindingVariable() {
        return BR.feedItem;
    }

    @Override
    public int getLayoutId() {
        return R.layout.feed_item_fragment;
    }

    @Override
    public BaseViewModel getViewModel() {
        return new FeedItemViewModel(feedItem);
    }

    public void setFeedItem(Feed feedItem) {
        this.feedItem = feedItem;
    }
}
