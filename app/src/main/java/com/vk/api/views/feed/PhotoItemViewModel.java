package com.vk.api.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;

public class PhotoItemViewModel extends BaseObservable {

    private final FeedPhotoAttachment item;

    public PhotoItemViewModel(FeedPhotoAttachment item){
        this.item = item;
    }

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @Bindable
    public String getUrl() {
        return item.getUrl();
    }
}
