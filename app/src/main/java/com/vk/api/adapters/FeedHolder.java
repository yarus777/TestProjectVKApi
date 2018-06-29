package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vk.api.databinding.FeedItemBinding;
import com.vk.api.fragments.feed.FeedItemClickListener;
import com.vk.api.views.feed.FeedItemViewModel;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedHolder extends RecyclerView.ViewHolder  {

    private FeedItemBinding binding;

    FeedHolder(FeedItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    void bind(final Feed item, final FeedItemClickListener listener) {
        FeedItemViewModel itemViewModel = new FeedItemViewModel(item);
        binding.setFeedItem(itemViewModel);
        binding.executePendingBindings();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }


}
