package test.project.vkapi.adapters;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.databinding.LinkItemBinding;
import test.project.vkapi.views.feed.LinkItemViewModel;

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
