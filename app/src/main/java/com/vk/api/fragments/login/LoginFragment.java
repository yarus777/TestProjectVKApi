package com.vk.api.fragments.login;


import com.android.databinding.library.baseAdapters.BR;
import com.vk.api.R;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.BaseViewModel;


public class LoginFragment extends BaseFragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    private static LoginListener loginListener;


    public static LoginFragment newInstance(LoginListener listener) {
        LoginFragment fragment = new LoginFragment();
        loginListener = listener;
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    public BaseViewModel getViewModel() {
        return new LoginFragmentViewModel(loginListener);
    }
}
