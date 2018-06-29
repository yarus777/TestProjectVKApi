package com.vk.api.fragments.feed;

import com.android.databinding.library.baseAdapters.BR;
import com.vk.api.R;
import com.vk.api.di.AppComponent;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.BaseViewModel;
import com.vk.api.fragments.login.LoginListener;

import javax.inject.Inject;


public class FeedFragment extends BaseFragment {
    public static final String TAG = FeedFragment.class.getSimpleName();

    private FeedItemClickListener listener;

    @Inject
    FeedFragmentViewModel viewModel;

    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        return fragment;
    }

    public void setListener(FeedItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    protected void inject(AppComponent injector) {
        injector.inject(this);
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
        viewModel.setListener(listener);
        return viewModel;
    }
}
