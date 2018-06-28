package com.vk.api.fragments.about;

import com.android.databinding.library.baseAdapters.BR;
import com.vk.api.R;
import com.vk.api.di.AppComponent;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.BaseViewModel;

public class AboutFragment extends BaseFragment {

    public static final String TAG = AboutFragment.class.getSimpleName();

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    protected void inject(AppComponent injector) {

    }

    @Override
    public int getBindingVariable() {
        return BR.aboutViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.about_fragment;
    }

    @Override
    public BaseViewModel getViewModel() {
        return new AboutFragmentViewModel();
    }
}
