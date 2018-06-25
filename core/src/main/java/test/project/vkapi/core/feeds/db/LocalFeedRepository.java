package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.db.models.PostSourceModel;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.core.feeds.models.FeedVideoAttachment;

public class LocalFeedRepository implements FeedRepository, FeedStorage {

    private final FeedDAO dao;
    private final ModelMapper mapper;
    private final AttachmentsRepository attachmentsRepository;
    private final PostSourceRepository postSourceRepository;

    @Inject
    LocalFeedRepository(FeedDAO dao, ModelMapper mapper, AttachmentsRepository attachmentsRepository, PostSourceRepository postSourceRepository) {
        this.dao = dao;
        this.mapper = mapper;
        this.attachmentsRepository = attachmentsRepository;
        this.postSourceRepository = postSourceRepository;
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
        FeedDBModel feedDBModel;
        PostSourceModel postSourceModel;
        for (Feed feed : feeds) {
            feedDBModel = mapper.map(feed, FeedDBModel.class);
            postSourceModel = mapper.map(feed.getSource(), PostSourceModel.class);
            postSourceRepository.save(postSourceModel);
            feedDBModel.setPostId(postSourceModel.getId());
            dao.insert(feedDBModel);

            for (FeedLinkAttachment attachment : feed.getLinkAttachmentList()) {
                attachmentsRepository.save(feed.getId(), attachment);
            }
            for (FeedPhotoAttachment attachment : feed.getPhotoAttachmentList()) {
                attachmentsRepository.save(feed.getId(), attachment);
            }
            for (FeedVideoAttachment attachment : feed.getVideoAttachmentList()) {
                attachmentsRepository.save(feed.getId(), attachment);
            }
            for (FeedAudioAttachment attachment : feed.getAudioAttachmentList()) {
                attachmentsRepository.save(feed.getId(), attachment);
            }
        }
    }
}
