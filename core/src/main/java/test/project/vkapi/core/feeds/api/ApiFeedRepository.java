package test.project.vkapi.core.feeds.api;

import org.modelmapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.feeds.api.models.FeedList;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.api.models.FeedResponse;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.user.UserManager;

public class ApiFeedRepository implements FeedRepository {

    private final VkApi api;
    private final UserManager userManager;
    private final ModelMapper mapper;

    private final FeedRepository dbRepository;
    private final FeedStorage feedStorage;

    @Inject
    public ApiFeedRepository(VkApi api, UserManager userManager, ModelMapper mapper, @Named("DB") FeedRepository localDbRepository, FeedStorage feedStorage) {
        this.api = api;
        this.userManager = userManager;
        this.mapper = mapper;
        this.dbRepository = localDbRepository;
        this.feedStorage = feedStorage;
    }

    @Override
    public Observable<List<Feed>> getFeed() {
        return api
                .getFeed(userManager.getToken(), "5.78", 100, "post")
                .map(new Function<FeedResponse, FeedList>() {
                    @Override
                    public FeedList apply(FeedResponse feedResponse) throws Exception {
                        return feedResponse.getFeedList();
                    }
                })
                .map(new Function<FeedList, List<Feed>>() {
                    @Override
                    public List<Feed> apply(FeedList feedList) throws Exception {
                        // map here
                        return null;
                    }
                })
                .doOnSuccess(new Consumer<List<Feed>>() {
                    @Override
                    public void accept(List<Feed> feeds) throws Exception {
                        feedStorage.save(feeds);
                    }
                })
                .toObservable()
                .mergeWith(dbRepository.getFeed())
                .distinct();
    }
}
