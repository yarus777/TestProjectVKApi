package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;

import com.vk.api.databinding.FeedItemBinding;
import com.vk.api.views.feed.FeedItemViewModel;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedHolder extends RecyclerView.ViewHolder  {

    private FeedItemBinding binding;

    FeedHolder(FeedItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    void bind(Feed item) {
        FeedItemViewModel itemViewModel = new FeedItemViewModel(item);
        binding.setFeedItem(itemViewModel);
        binding.executePendingBindings();
    }


}
