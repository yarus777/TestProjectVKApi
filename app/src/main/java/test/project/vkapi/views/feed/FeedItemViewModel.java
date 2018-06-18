package test.project.vkapi.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.adapters.PhotoAdapter;
import test.project.vkapi.core.feeds.api.models.FeedItem;
import test.project.vkapi.core.feeds.api.models.PostInfoSource;
import test.project.vkapi.core.feeds.models.Feed;

public class FeedItemViewModel extends BaseObservable {
    private final Feed item;
    private final PhotoAdapter adapter;

    public FeedItemViewModel(Feed item) {
        this.item = item;
        adapter = new PhotoAdapter();
        adapter.setItems((item.getPhotoAttachmentList().size() > 1) ? (item.getPhotoAttachmentList().subList(1, item.getPhotoAttachmentList().size() - 1)) : null);
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
    public PhotoAdapter getAdapter() {
        return adapter;
    }

    @BindingAdapter({"app:adapter"})
    public static void bind(RecyclerView recyclerView, FeedAdapter adapter) {
        recyclerView.setAdapter(adapter);
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
        return item.getPhotoAttachmentList().size() > 0 ? item.getPhotoAttachmentList().get(0).getUrl() : "";
    }

    @BindingAdapter({"app:attachmentUrl"})
    public static void setAttachmentImg(ImageView imageView, String attachmentUrl) {
        Glide.with(imageView.getContext())
                .load(attachmentUrl)
                .into(imageView);
    }


}
