package com.vk.api.adapters;

import android.support.v7.widget.RecyclerView;

import com.vk.api.databinding.AudioItemBinding;
import com.vk.api.views.feed.AudioItemViewModel;

import test.project.vkapi.core.feeds.models.FeedAudioAttachment;

public class AudioHolder extends RecyclerView.ViewHolder {
    private AudioItemBinding binding;

    AudioHolder(AudioItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(FeedAudioAttachment item) {
        AudioItemViewModel audioItemViewModel = new AudioItemViewModel(item);
        binding.setAudioItem(audioItemViewModel);
        binding.executePendingBindings();
    }
}
