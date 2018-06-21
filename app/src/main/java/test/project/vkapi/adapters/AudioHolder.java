package test.project.vkapi.adapters;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.databinding.AudioItemBinding;
import test.project.vkapi.views.feed.AudioItemViewModel;

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
