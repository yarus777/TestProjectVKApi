package test.project.vkapi.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import test.project.vkapi.adapters.FeedAdapter;
import test.project.vkapi.adapters.PhotoAdapter;
import test.project.vkapi.core.api.feed.FeedItem;

public class FeedItemViewModel extends BaseObservable {
    private final FeedItem item;
    private final PhotoAdapter adapter;

    public FeedItemViewModel(FeedItem item) {
        this.item = item;
        adapter = new PhotoAdapter();
        adapter.setItems(item.getPhotoAttachments());
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
}
