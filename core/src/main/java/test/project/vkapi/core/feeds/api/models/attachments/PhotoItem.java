package test.project.vkapi.core.feeds.api.models.attachments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoItem extends AttachmentItem {

    @SerializedName("sizes")
    private List<SizesItem> sizes;

    public void setSizes(List<SizesItem> sizes) {
        this.sizes = sizes;
    }

    public SizesItem chooseBiggestPhotoItem() {
        SizesItem choseItem = new SizesItem();
        for (SizesItem item : sizes) {
            if (item.getWidth() > choseItem.getWidth()) {
                choseItem = item;
            }
        }
        return choseItem;
    }


}


