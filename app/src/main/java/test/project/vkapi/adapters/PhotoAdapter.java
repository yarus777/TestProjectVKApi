package test.project.vkapi.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.R;
import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.databinding.PhotoItemBinding;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

    private List<FeedPhotoAttachment> items = new ArrayList<>();

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PhotoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.photo_item, parent, false);
        return new PhotoHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<FeedPhotoAttachment> photos) {
        if (photos != null) {
            items.clear();
            items.addAll(photos);
            notifyDataSetChanged();
        }

    }
}
