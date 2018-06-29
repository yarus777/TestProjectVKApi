package com.vk.api.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vk.api.R;
import com.vk.api.databinding.FeedItemBinding;
import com.vk.api.fragments.feed.FeedItemClickListener;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private List<Feed> items = new ArrayList<>();
    private FeedItemClickListener listener;

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FeedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.feed_item, parent, false);
        return new FeedHolder(binding);
    }

    public void setListener(FeedItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        holder.bind(items.get(position), listener);
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
