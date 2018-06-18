package test.project.vkapi.core.feeds.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FeedPhotoAttachment extends BaseObservable {

    private String url;

    private int width;

    private int height;

    public FeedPhotoAttachment() {

    }

    public FeedPhotoAttachment(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
