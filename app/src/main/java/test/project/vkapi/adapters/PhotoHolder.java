package test.project.vkapi.adapters;

import android.support.v7.widget.RecyclerView;

import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.databinding.PhotoItemBinding;
import test.project.vkapi.views.feed.PhotoItemViewModel;

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
