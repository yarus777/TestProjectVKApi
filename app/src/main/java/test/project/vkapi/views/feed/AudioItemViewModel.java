package test.project.vkapi.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import test.project.vkapi.core.feeds.models.FeedAudioAttachment;


public class AudioItemViewModel extends BaseObservable {

    private final FeedAudioAttachment item;

    public AudioItemViewModel(FeedAudioAttachment item) {
        this.item = item;
    }

    @Bindable
    public String getArtist() {
        return item.getArtist();
    }

    @Bindable
    public String getTitle() {
        return item.getTitle();
    }

    @Bindable
    public String getDuration() {
        return String.valueOf(item.getDuration());
    }
}
