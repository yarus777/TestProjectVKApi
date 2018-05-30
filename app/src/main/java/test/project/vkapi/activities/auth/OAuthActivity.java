package test.project.vkapi.activities.auth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import test.project.vkapi.R;
import test.project.vkapi.activities.BaseActivity;

public class OAuthActivity extends BaseActivity {
    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    public static final String TOKEN = "access_token";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            /*@Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request.getUrl();
                if(url.getQueryParameter("error") != null) {
                    setResult(RESULT_CANCELED);
                    finish();
                } else if(url.toString().startsWith(REDIRECT_URL)) {
                    String token = parseUrlFragment(url.getFragment()).get("access_token");
                    Intent intent = new Intent();
                    intent.putExtra(TOKEN, token);
                    setResult(RESULT_OK, intent);
                    finish();
                    return false;
                }

                return super.shouldOverrideUrlLoading(view, request);
            }*/

            @Override
            public void onPageStarted(WebView view, String uri, Bitmap favicon) {
                super.onPageStarted(view, uri, favicon);
                if(!uri.startsWith(REDIRECT_URL)) {
                    return;
                }
                String token = parseUrlFragment(uri.replace(REDIRECT_URL, "").replace("#", "")).get("access_token");
                Intent intent = new Intent();
                intent.putExtra(TOKEN, token);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("oauth.vk.com")
                .appendPath("authorize")
                .appendQueryParameter("client_id", String.valueOf(getResources().getInteger(R.integer.com_vk_sdk_AppId)))
                .appendQueryParameter("redirect_uri", REDIRECT_URL)
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("scope", "wall,friends")
                .appendQueryParameter("v", "5.77");
        webView.loadUrl(builder.build().toString());
    }

    private Map<String, String> parseUrlFragment (String url) {
        HashMap<String, String> output = new HashMap<> ();
        String[] keys = url.split ("&");

        for (String key : keys) {
            String[] values = key.split ("=");
            output.put (values[0], (values.length > 1 ? values[1] : ""));
        }
        return output;
    }
}
