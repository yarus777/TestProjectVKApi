package test.project.vkapi;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.databinding.FeedItemBinding;

public class FeedHolder extends RecyclerView.ViewHolder {

    private FeedItemBinding binding;

    FeedHolder(FeedItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FeedItem item) {
        binding.setFeedItem(item);
        binding.executePendingBindings();
    }


}
