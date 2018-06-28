package com.vk.api.fragments.login;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vk.api.fragments.BaseViewModel;

import java.util.HashMap;
import java.util.Map;


public class LoginFragmentViewModel extends BaseViewModel {

    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";


    private LoginListener loginListener;

    LoginFragmentViewModel(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @BindingAdapter({"setWebViewClient"})
    public static void setWebViewClient(WebView webView, WebViewClient client) {
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(client);
    }

    @Bindable
    public WebViewClient getClient() {
        return new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String uri, Bitmap favicon) {
                super.onPageStarted(view, uri, favicon);
                if (!uri.startsWith(REDIRECT_URL)) {
                    return;
                }
                String token = parseUrlFragment(uri.replace(REDIRECT_URL, "").replace("#", "")).get("access_token");
                loginListener.onTokenRecieved(token);
            }
        };
    }

    @Bindable
    public String getUrl() {
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

    @BindingAdapter({"loadUrl"})
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
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
