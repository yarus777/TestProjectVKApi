package com.vk.api.fragments.feed;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vk.api.R;
import com.vk.api.activities.MainActivity;
import com.vk.api.adapters.FeedAdapter;
import com.vk.api.databinding.FeedItemBinding;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.feed.item.FeedItemFragment;
import com.vk.api.views.feed.FeedItemViewModel;

import java.util.List;

import test.project.vkapi.core.feeds.models.Feed;


public class FeedFragment extends BaseFragment<FeedFragmentViewModel> {
    public static final String TAG = FeedFragment.class.getSimpleName();

    private RecyclerView feedListView;
    private FeedAdapter adapter;

    public FeedFragment() {
        super(new FeedFragmentViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feedListView = view.findViewById(R.id.feed_recycler);
        adapter = new FeedAdapter(getNavigator());
        feedListView.setAdapter(adapter);

        getViewModel().feeds().observe(this, new Observer<List<Feed>>() {
            @Override
            public void onChanged(@Nullable List<Feed> feeds) {
                adapter.setItems(feeds);
            }
        });
    }

    public static class FeedHolder extends RecyclerView.ViewHolder  {
        private Feed item;

        private TextView description;

        public FeedHolder(View view, final MainActivity navigator) {
            super(view);
            description = view.findViewById(R.id.post_text);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item != null) {
                        navigator.goTo(new FeedItemFragment(new FeedItemViewModel(item)));
                    }
                }
            });
        }

        public void bind(final Feed item) {
            this.item = item;
            description.setText(item.getText());
        }
    }
}
