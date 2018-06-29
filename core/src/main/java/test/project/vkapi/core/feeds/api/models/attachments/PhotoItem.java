package test.project.vkapi.core.feeds.api.models.attachments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoItem  {

    @SerializedName("id")
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


