package test.project.vkapi.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import test.project.vkapi.adapters.AudioAdapter;

import test.project.vkapi.adapters.LinkAdapter;
import test.project.vkapi.adapters.PhotoAdapter;
import test.project.vkapi.core.feeds.models.Feed;


public class FeedItemViewModel extends BaseObservable {
    private final Feed item;
    private final PhotoAdapter photoAdapter;
    private final AudioAdapter audioAdapter;
    private final LinkAdapter linkAdapter;

    public FeedItemViewModel(Feed item) {
        this.item = item;
        photoAdapter = new PhotoAdapter();
        photoAdapter.setItems(getPhotoAttachmentsRecyclerVisibility() ? (item.getPhotoAttachmentList().subList(1, item.getPhotoAttachmentList().size() - 1)) : null);
        audioAdapter = new AudioAdapter();
        audioAdapter.setItems(item.getAudioAttachmentList());
        linkAdapter = new LinkAdapter();
        linkAdapter.setItems(item.getLinkAttachmentList());
    }

    @Bindable
    public String getText() {
        return item.getText();
    }

    @Bindable
    public int getLikesCount() {
        return item.getLikesCount();
    }

    @Bindable
    public int getCommentsCount() {
        return item.getCommentsCount();
    }

    @Bindable
    public LinkAdapter getLinkAdapter() {
        return linkAdapter;
    }

    @BindingAdapter({"app:linkAdapter"})
    public static void bind(RecyclerView recyclerView, LinkAdapter linkAdapter) {
        recyclerView.setAdapter(linkAdapter);
    }

    @Bindable
    public AudioAdapter getAudioAdapter() {
        return audioAdapter;
    }

    @BindingAdapter({"app:audioAdapter"})
    public static void bind(RecyclerView recyclerView, AudioAdapter audioAdapter) {
        recyclerView.setAdapter(audioAdapter);
    }

    @Bindable
    public PhotoAdapter getPhotoAdapter() {
        return photoAdapter;
    }

    @BindingAdapter({"app:photoAdapter"})
    public static void bind(RecyclerView recyclerView, PhotoAdapter photoAdapter) {
        recyclerView.setAdapter(photoAdapter);
    }

    @Bindable
    public String getPostUserText() {
        return item.getSource().getPostUserText();
    }

    @Bindable
    public String getPostUserImg() {
        return item.getSource().getImgUrl();
    }

    @BindingAdapter({"app:url"})
    public static void setImg(ImageView imageView, String postUserImg) {
        Glide.with(imageView.getContext())
                .load(postUserImg)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }

    @Bindable
    public String getAttachmentImg() {
        return getPhotoAttachmentVisibility() ? item.getPhotoAttachmentList().get(0).getUrl() : "";
    }

    @BindingAdapter({"app:attachmentUrl"})
    public static void setAttachmentImg(ImageView imageView, String attachmentUrl) {
        Glide.with(imageView.getContext())
                .load(attachmentUrl)
                .into(imageView);
    }

    @Bindable
    public boolean getPhotoAttachmentsRecyclerVisibility() {
        return item.getPhotoAttachmentList().size() > 1;
    }

    @Bindable
    public boolean getPhotoAttachmentVisibility() {
        return item.getPhotoAttachmentList().size() > 0;
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
    }

}
