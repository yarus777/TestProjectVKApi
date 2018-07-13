package com.vk.api.views.feed;

import com.vk.api.adapters.AudioAdapter;
import com.vk.api.adapters.LinkAdapter;
import com.vk.api.adapters.PhotoAdapter;
import com.vk.api.fragments.BaseViewModel;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedItemViewModel extends BaseViewModel {

    //private LiveData<Feed> item;

    private final Feed item;
    //private final PhotoAdapter photoAdapter;
    //private final AudioAdapter audioAdapter;
    //private final LinkAdapter linkAdapter;

    public FeedItemViewModel(Feed item) {
        this.item = item;
        //photoAdapter = new PhotoAdapter();
        //photoAdapter.setItems(getPhotoAttachmentsRecyclerVisibility() ? (item.getPhotoAttachmentList().subList(1, item.getPhotoAttachmentList().size() - 1)) : null);
        //audioAdapter = new AudioAdapter();
        //audioAdapter.setItems(item.getAudioAttachmentList());
        //linkAdapter = new LinkAdapter();
        //linkAdapter.setItems(item.getLinkAttachmentList());
    }


    /*@Bindable
    public LinkAdapter getLinkAdapter() {
        return linkAdapter;
    }

    @BindingAdapter({"linkAdapter"})
    public static void bind(RecyclerView recyclerView, LinkAdapter linkAdapter) {
        recyclerView.setAdapter(linkAdapter);
    }

    @Bindable
    public AudioAdapter getAudioAdapter() {
        return audioAdapter;
    }

    @BindingAdapter({"audioAdapter"})
    public static void bind(RecyclerView recyclerView, AudioAdapter audioAdapter) {
        recyclerView.setAdapter(audioAdapter);
    }

    @Bindable
    public PhotoAdapter getPhotoAdapter() {
        return photoAdapter;
    }

    @BindingAdapter({"photoAdapter"})
    public static void bind(RecyclerView recyclerView, PhotoAdapter photoAdapter) {
        recyclerView.setAdapter(photoAdapter);
    }

    @BindingAdapter({"attachmentUrl"})
    public static void setAttachmentImg(ImageView imageView, String attachmentUrl) {
        Glide.with(imageView.getContext())
                .load(attachmentUrl)
                .into(imageView);
    }

    @Bindable
    public boolean getPhotoAttachmentsRecyclerVisibility() {
        return item.getPhotoAttachmentList().size() > 1;
    }


    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @Bindable
    public boolean getAudioAttachmentsRecyclerVisibility() {
        return item.getAudioAttachmentList().size() > 0;
    }

    @Bindable
    public boolean getLinkAttachmentsRecyclerVisibility() {
        return item.getLinkAttachmentList().size() > 0;
    }*/

}
