package com.vk.api.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.vk.api.activities.MainActivity;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    protected MainActivity mMainActivity;

    private V mViewModel;

    protected BaseFragment(V viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof MainActivity)
            mMainActivity = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    protected V getViewModel() {
        return mViewModel;
    }

    public MainActivity getNavigator() {
        return mMainActivity;
    }

}
