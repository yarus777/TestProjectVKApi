package test.project.vkapi.adapters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import test.project.vkapi.core.api.feed.PhotoItem;
import test.project.vkapi.databinding.PhotoItemBinding;

public class PhotoHolder extends RecyclerView.ViewHolder {

    private PhotoItemBinding binding;

    PhotoHolder(PhotoItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(PhotoItem item) {
        binding.setPhotoItem(item);
        binding.executePendingBindings();
    }

}
