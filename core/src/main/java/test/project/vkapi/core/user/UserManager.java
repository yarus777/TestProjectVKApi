package test.project.vkapi.core.user;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.webkit.CookieManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import test.project.vkapi.core.storage.DataStorage;

@Singleton
public class UserManager extends BaseObservable{
    private static final String TOKEN = "token";

    private String token;
    private final DataStorage dataStorage;

    @Inject
    public UserManager(DataStorage dataStorage) {
        token = dataStorage.get(TOKEN);
        this.dataStorage = dataStorage;
    }


    public String getToken() {
        return token;
    }

    public void login(String token) {
        this.token = token;
        save();
    }

    @Bindable
    public boolean isAuthorized() {
        return token != null && !token.isEmpty();
    }

    public void logout() {
        token = null;
        CookieManager.getInstance().removeAllCookies(null);
        save();
    }

    private void save() {
        dataStorage.save(TOKEN, token);
    }
}
