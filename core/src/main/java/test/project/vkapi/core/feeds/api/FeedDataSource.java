package test.project.vkapi.core.feeds.api;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import io.reactivex.functions.Consumer;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.user.UserManager;

public class FeedDataSource extends PageKeyedDataSource<String, Feed> {
    private final UserManager userManager;
    private final FeedRepository feedRepository;

    public FeedDataSource(FeedRepository feedRepository, UserManager userManager) {
        this.feedRepository = feedRepository;
        this.userManager = userManager;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Feed> callback) {
        userManager.getToken().subscribe(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                feedRepository
                        .getFeed(token, null, params.requestedLoadSize)
                        .subscribe(
                                new Consumer<FeedPageData>() {
                                    @Override
                                    public void accept(FeedPageData feedsData) throws Exception {
                                        callback.onResult(feedsData.feeds, null, feedsData.nextFrom);
                                    }
                                }
                        );
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<String> params, @NonNull final LoadCallback<String, Feed> callback) {
        userManager.getToken().subscribe(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                feedRepository
                        .getFeed(token, params.key, params.requestedLoadSize)
                        .subscribe(
                                new Consumer<FeedPageData>() {
                                    @Override
                                    public void accept(FeedPageData feedsData) throws Exception {
                                        callback.onResult(feedsData.feeds, feedsData.nextFrom);
                                    }
                                }
                        );
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<String> params, @NonNull final LoadCallback<String, Feed> callback) {
        userManager.getToken().subscribe(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                feedRepository
                        .getFeed(token, params.key, params.requestedLoadSize)
                        .subscribe(
                                new Consumer<FeedPageData>() {
                                    @Override
                                    public void accept(FeedPageData feedsData) throws Exception {
                                        callback.onResult(feedsData.feeds, feedsData.nextFrom);
                                    }
                                }
                        );
            }
        });
    }
}
