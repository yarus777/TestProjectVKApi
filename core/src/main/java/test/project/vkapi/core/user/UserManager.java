package test.project.vkapi.core.user;

import android.webkit.CookieManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import test.project.vkapi.core.storage.DataStorage;

@Singleton
public class UserManager {
    private static final String TOKEN = "token";
    private Subject<String> token = BehaviorSubject.create();
    private Subject<Boolean> isSignedIn = BehaviorSubject.create();

    @Inject
    public UserManager(final DataStorage dataStorage) {
        token.subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        dataStorage.save(TOKEN, s);
                        isSignedIn.onNext(s != null && !s.isEmpty());
                    }
                }
        );
        String t = dataStorage.get(TOKEN);
        if(t != null) {
            token.onNext(t);
        } else {
            isSignedIn.onNext(false);
        }
    }

    public Observable<String> getToken() {
        return token;
    }

    public Observable<Boolean> isSignedIn() {
        return isSignedIn;
    }

    public void login(String token) {
        this.token.onNext(token);
    }

    public void logout() {
        token.onNext(null);
        CookieManager.getInstance().removeAllCookies(null);
    }
}
