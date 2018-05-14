package test.project.vkapi;

import android.support.multidex.MultiDexApplication;

import com.vk.sdk.VKSdk;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
