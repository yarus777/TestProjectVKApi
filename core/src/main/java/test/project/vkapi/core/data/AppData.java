package test.project.vkapi.core.data;

import test.project.vkapi.core.data.modules.FeedModule;

public class AppData {
    private static FeedModule feedModule;

    public static void init(FeedModule feedModule) {
        AppData.feedModule = feedModule;
    }

    public static FeedModule feed() {
        return feedModule;
    }
}
