package test.project.vkapi.core.feeds.api.models.attachments;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoItem extends AttachmentItem {

    @SerializedName("sizes")
    private List<SizesItem> sizes;


    public String chooseBiggestPhotoItemUrl() {
        SizesItem choseItem = new SizesItem();
        for (SizesItem item : sizes) {
            if (item.getWidth() > choseItem.getWidth()) {
                choseItem = item;
            }
        }
        return choseItem.getUrl();
    }

    @Bindable
    public String getImageUrl() {
        return chooseBiggestPhotoItemUrl();
    }


    public void setSizes(List<SizesItem> sizes) {
        this.sizes = sizes;
    }

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}


@Entity
class SizesItem extends BaseObservable {
    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
