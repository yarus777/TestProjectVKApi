package test.project.vkapi.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.project.vkapi.R;
import test.project.vkapi.core.feeds.api.models.FeedItem;
import test.project.vkapi.core.feeds.api.models.FeedList;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.databinding.FeedItemBinding;

public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private List<Feed> items = new ArrayList<>();

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FeedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.feed_item, parent, false);
        return new FeedHolder(binding);
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Feed> feeds) {
        items.clear();
        items.addAll(feeds);
        notifyDataSetChanged();
    }

}
