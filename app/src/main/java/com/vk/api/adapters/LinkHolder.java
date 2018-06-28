package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;

import com.vk.api.databinding.LinkItemBinding;
import com.vk.api.views.feed.LinkItemViewModel;

import test.project.vkapi.core.feeds.models.FeedLinkAttachment;


public class LinkHolder extends RecyclerView.ViewHolder {
    private LinkItemBinding binding;

    LinkHolder(LinkItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(FeedLinkAttachment item) {
        LinkItemViewModel linkItemViewModel = new LinkItemViewModel(item);
        binding.setLinkItem(linkItemViewModel);
        binding.executePendingBindings();
    }
}
