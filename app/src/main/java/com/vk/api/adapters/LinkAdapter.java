package com.vk.api.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vk.api.R;
import com.vk.api.databinding.LinkItemBinding;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.feeds.models.FeedLinkAttachment;

public class LinkAdapter extends RecyclerView.Adapter<LinkHolder>{

    private List<FeedLinkAttachment> items = new ArrayList<>();

    @NonNull
    @Override
    public LinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LinkItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.link_item, parent, false);
        return new LinkHolder(binding);
    }

    @Override
    public void onBindViewHolder(LinkHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<FeedLinkAttachment> links) {
        if (links != null) {
            items.clear();
            items.addAll(links);
            notifyDataSetChanged();
        }

    }
}
