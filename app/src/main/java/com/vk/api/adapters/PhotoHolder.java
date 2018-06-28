package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;

import com.vk.api.databinding.PhotoItemBinding;
import com.vk.api.views.feed.PhotoItemViewModel;

import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;

public class PhotoHolder extends RecyclerView.ViewHolder {

    private PhotoItemBinding binding;

    PhotoHolder(PhotoItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(FeedPhotoAttachment item) {
        PhotoItemViewModel photoItemViewModel = new PhotoItemViewModel(item);
        binding.setPhotoItem(photoItemViewModel);
        binding.executePendingBindings();
    }

}
