package com.vk.api.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.api.R;
import com.vk.api.activities.MainActivity;
import com.vk.api.fragments.feed.FeedFragment;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedAdapter extends PagedListAdapter<Feed, FeedFragment.FeedHolder> {
    private final MainActivity navigator;

    public FeedAdapter(MainActivity navigator, DiffUtil.ItemCallback<Feed> callback) {
        super(callback);
        this.navigator = navigator;
    }

    @Override
    public FeedFragment.FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedFragment.FeedHolder(v, navigator);
    }

    @Override
    public void onBindViewHolder(FeedFragment.FeedHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
