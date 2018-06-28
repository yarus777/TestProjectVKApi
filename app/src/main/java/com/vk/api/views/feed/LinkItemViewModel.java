package com.vk.api.views.feed;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import test.project.vkapi.core.feeds.models.FeedLinkAttachment;

public class LinkItemViewModel extends BaseObservable {
    private final FeedLinkAttachment item;

    public LinkItemViewModel(FeedLinkAttachment item){
        this.item = item;
    }

    @Bindable
    public String getUrl(){
        return item.getUrl();
    }

    @Bindable
    public String getDescription(){
        return item.getDescription();
    }

    @Bindable
    public String getTitle(){
        return item.getTitle();
    }
}
