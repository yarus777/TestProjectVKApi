package com.vk.api.fragments.login;

public interface LoginListener {
    void onTokenReceived(String token);

    void onNotAuthorized();

    void onAuthorized();
}
