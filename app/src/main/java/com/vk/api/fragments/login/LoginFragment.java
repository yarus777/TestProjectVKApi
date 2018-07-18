package com.vk.api.fragments.login;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vk.api.R;
import com.vk.api.fragments.BaseFragment;
import com.vk.api.fragments.feed.FeedFragment;

import java.util.HashMap;
import java.util.Map;

import test.project.vkapi.core.data.AppData;


public class LoginFragment extends BaseFragment<LoginFragmentViewModel> {
    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    public LoginFragment() {
        super(new LoginFragmentViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView webView = view.findViewById(R.id.web_view);
        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String uri, Bitmap favicon) {
                super.onPageStarted(view, uri, favicon);
                if (!uri.startsWith(REDIRECT_URL)) {
                    return;
                }
                String token = parseUrlFragment(uri.replace(REDIRECT_URL, "").replace("#", "")).get("access_token");
                AppData.auth().login(token);
                getNavigator().goTo(new FeedFragment(), true);
            }
        };
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(client);
        webView.loadUrl(getUrl());
    }

    private String getUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("oauth.vk.com")
                .appendPath("authorize")
                .appendQueryParameter("client_id", String.valueOf(6618894))
                .appendQueryParameter("redirect_uri", REDIRECT_URL)
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("scope", "wall,friends,audio,video")
                .appendQueryParameter("v", "5.8");
        return builder.build().toString();
    }

    private Map<String, String> parseUrlFragment(String url) {
        HashMap<String, String> output = new HashMap<>();
        String[] keys = url.split("&");

        for (String key : keys) {
            String[] values = key.split("=");
            output.put(values[0], (values.length > 1 ? values[1] : ""));
        }
        return output;
    }
}
