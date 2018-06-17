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
import test.project.vkapi.databinding.FeedItemBinding;

public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private List<FeedItem> items = new ArrayList<>();
    private FeedList feedList;

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FeedItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.feed_item, parent, false);
        return new FeedHolder(binding);
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        holder.bind(items.get(position), feedList.getInfoItems().get(items.get(position).getSourceId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<FeedItem> feeds, FeedList feedList) {
        items.clear();
        items.addAll(feeds);
        this.feedList = feedList;
        notifyDataSetChanged();
    }

}
