package test.project.vkapi.core.feeds.api;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.user.UserManager;

public class DataSourceFactory /*implements DataSource.Factory*/ {
   /* private MutableLiveData<FeedDataSource> dataSource = new MutableLiveData<>();
    private final FeedRepository feedRepository;
    private final UserManager userManager;

    public DataSourceFactory(FeedRepository feedRepository, UserManager userManager) {
        this.feedRepository = feedRepository;
        this.userManager = userManager;
    }

    @Override
    public DataSource<String, Feed> create() {
        FeedDataSource source = new FeedDataSource(feedRepository, userManager);
        dataSource.postValue(source);
        return source;
    }*/
}
