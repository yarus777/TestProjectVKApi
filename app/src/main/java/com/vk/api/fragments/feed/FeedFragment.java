package com.vk.api.fragments.feed;

import com.android.databinding.library.baseAdapters.BR;
import com.vk.api.R;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.BaseViewModel;

import javax.inject.Inject;

public class FeedFragment extends BaseFragment {
    public static final String TAG = FeedFragment.class.getSimpleName();

    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.feedViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.feed_fragment;
    }

    @Override
    public BaseViewModel getViewModel() {
        return new FeedFragmentViewModel();
    }
}
