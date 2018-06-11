package test.project.vkapi.adapters;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.api.feed.AttachmentItem;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.PhotoItem;
import test.project.vkapi.databinding.FeedItemBinding;


public class FeedHolder extends RecyclerView.ViewHolder implements Observable {

    private FeedItemBinding binding;
    private PhotoAdapter photoAdapter;

    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    FeedHolder(FeedItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    void bind(FeedItem item) {
        binding.setFeedItem(item);
        binding.setFeedHolder(this);
        photoAdapter = new PhotoAdapter();
        photoAdapter.setItems(item.getPhotoAttachments());
        binding.executePendingBindings();
    }

    @Bindable
    public PhotoAdapter getPhotoAdapter() {
        return photoAdapter;
    }

    @BindingAdapter({"photoAdapter"})
    public static void bind(RecyclerView recyclerView, PhotoAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.remove(callback);
    }
}
