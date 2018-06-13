package test.project.vkapi.core.api.feed.attachments;

import com.google.gson.annotations.SerializedName;

import test.project.vkapi.core.api.feed.attachments.AttachmentItem;

public class AudioItem extends AttachmentItem {

    @SerializedName("artist")
    private String artist;

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private int duration;

    @SerializedName("url")
    private String url;
}
