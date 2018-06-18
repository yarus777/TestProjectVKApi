package test.project.vkapi.adapters;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.feeds.api.models.FeedItem;
import test.project.vkapi.core.feeds.api.models.PostInfoSource;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.databinding.FeedItemBinding;
import test.project.vkapi.views.feed.FeedItemViewModel;


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
