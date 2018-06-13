package test.project.vkapi.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.adapters.PhotoAdapter;
import test.project.vkapi.core.api.feed.FeedItem;
import test.project.vkapi.core.api.feed.PostInfoSource;

public class FeedItemViewModel extends BaseObservable {
    private final FeedItem item;
    private final PhotoAdapter adapter;
    private final PostInfoSource postInfoSource;

    public FeedItemViewModel(FeedItem item, PostInfoSource postInfoSource) {
        this.item = item;
        this.postInfoSource = postInfoSource;
        adapter = new PhotoAdapter();
        adapter.setItems((item.getPhotoAttachments().size() > 1) ? (item.getPhotoAttachments().subList(1, item.getPhotoAttachments().size() - 1)) : null);
    }

    @Bindable
    public String getText() {
        return item.getText();
    }

    @Bindable
    public int getLikesCount() {
        return item.getLikes().getLikeCount();
    }

    @Bindable
    public int getCommentsCount() {
        return item.getComments().getCommentCount();
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
        return postInfoSource.getPostUserText();
    }

    @Bindable
    public String getPostUserImg() {
        return postInfoSource.getImgUrl();
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
        return item.getPhotoAttachments().size() > 0 ? item.getPhotoAttachments().get(0).getImageUrl() : "";
    }

    @BindingAdapter({"app:attachmentUrl"})
    public static void setAttachmentImg(ImageView imageView, String attachmentUrl) {
        Glide.with(imageView.getContext())
                .load(attachmentUrl)
                .into(imageView);
    }


}
