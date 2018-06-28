package com.vk.api.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vk.api.R;
import com.vk.api.databinding.AudioItemBinding;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.core.feeds.models.FeedAudioAttachment;

public class AudioAdapter extends RecyclerView.Adapter<AudioHolder>{

    private List<FeedAudioAttachment> items = new ArrayList<>();

    @NonNull
    @Override
    public AudioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AudioItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.audio_item, parent, false);
        return new AudioHolder(binding);
    }

    @Override
    public void onBindViewHolder(AudioHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<FeedAudioAttachment> audios) {
        if (audios != null) {
            items.clear();
            items.addAll(audios);
            notifyDataSetChanged();
        }

    }
}
