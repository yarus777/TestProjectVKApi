package test.project.vkapi.adapters;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.databinding.PhotoItemBinding;

public class PhotoHolder extends RecyclerView.ViewHolder {

    private PhotoItemBinding binding;

    PhotoHolder(PhotoItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(FeedPhotoAttachment item) {
        binding.setPhotoItem(item);
        binding.executePendingBindings();
    }

}
