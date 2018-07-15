package test.project.vkapi.core.data;

import test.project.vkapi.core.data.modules.FeedModule;
import test.project.vkapi.core.data.modules.LoginModule;

public class AppData {
    private static FeedModule feedModule;
    private static LoginModule authModule;

    public static void init(FeedModule feedModule, LoginModule authModule) {
        AppData.feedModule = feedModule;
        AppData.authModule = authModule;
    }

    public static FeedModule feed() {
        return feedModule;
    }
    
    public static LoginModule auth() {
        return authModule;
    }
}
