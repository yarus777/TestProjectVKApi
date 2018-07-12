package com.vk.api.fragments.feed.item;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.vk.api.fragments.BaseFragment;
import com.vk.api.views.feed.FeedItemViewModel;

public class FeedItemFragment extends BaseFragment {
    public static final String TAG = FeedItemFragment.class.getSimpleName();

    public FeedItemFragment() {
        super(new FeedItemViewModel(null));
    }

    @SuppressLint("ValidFragment")
    public FeedItemFragment(FeedItemViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
