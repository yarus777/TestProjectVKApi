package test.project.vkapi.core.api.feed;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoItem extends AttachmentItem {

    @SerializedName("sizes")
    private List<SizesItem> sizes;

    public SizesItem chooseBiggestPhotoItem() {
        SizesItem choseItem = new SizesItem();
        for (SizesItem item : sizes) {
            if (item.getWidth() > choseItem.getWidth()) {
                choseItem = item;
            }
        }
        return choseItem;
    }

    public void setSizes(List<SizesItem> sizes) {
        this.sizes = sizes;
    }
}

@Entity
class SizesItem extends BaseObservable{
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
}
