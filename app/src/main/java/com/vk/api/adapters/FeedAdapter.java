package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.api.R;
import com.vk.api.activities.MainActivity;
import com.vk.api.fragments.feed.FeedFragment;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedAdapter extends RecyclerView.Adapter<FeedFragment.FeedHolder> {

    private final MainActivity navigator;
    private List<Feed> items = new ArrayList<>();

    public FeedAdapter(MainActivity navigator) {
        this.navigator = navigator;
    }

    @Override
    public FeedFragment.FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedFragment.FeedHolder(v, navigator);
    }

    @Override
    public void onBindViewHolder(FeedFragment.FeedHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Feed> feeds) {
        items.clear();
        items.addAll(feeds);
        notifyDataSetChanged();
    }

}
