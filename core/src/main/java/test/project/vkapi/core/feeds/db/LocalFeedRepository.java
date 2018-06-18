package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.user.db.models.UserDAO;

public class LocalFeedRepository implements FeedRepository, FeedStorage {

    private final FeedDAO dao;
    private final ModelMapper mapper;

    @Inject
    LocalFeedRepository(FeedDAO dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Feed>> getFeed() {
        return dao.getAll().map(new Function<List<FeedDBModel>, List<Feed>>() {
            @Override
            public List<Feed> apply(List<FeedDBModel> feedDBModels) throws Exception {
                List<Feed> feeds = new ArrayList<>();
                for (FeedDBModel dbModel : feedDBModels) {
                    feeds.add(mapper.map(dbModel, Feed.class));
                }
                return feeds;
            }
        }).toObservable();
    }

    @Override
    public void save(List<Feed> feeds) {

    }
}
