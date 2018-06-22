package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.db.models.AudioAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.db.models.LinkAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.PhotoAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.PostSourceModel;
import test.project.vkapi.core.feeds.db.models.VideoAttachmentsModel;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.core.feeds.models.FeedVideoAttachment;
import test.project.vkapi.core.user.db.models.UserDAO;

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
        PhotoAttachmentsModel photoAttachmentsModel;
        LinkAttachmentsModel linkAttachmentsModel;
        VideoAttachmentsModel videoAttachmentsModel;
        AudioAttachmentsModel audioAttachmentsModel;
        PostSourceModel postSourceModel;
        for (Feed feed : feeds) {
            feedDBModel = mapper.map(feed, FeedDBModel.class);
            postSourceModel = mapper.map(feed.getSource(), PostSourceModel.class);
            postSourceRepository.save(postSourceModel);
            feedDBModel.setPostId(postSourceModel.getId());
            dao.insert(feedDBModel);

            for (FeedLinkAttachment feedLinkAttachment : feed.getLinkAttachmentList()) {
                linkAttachmentsModel = mapper.map(feedLinkAttachment, LinkAttachmentsModel.class);
                linkAttachmentsModel.setFeedId(feed.getPostId());
                attachmentsRepository.save(linkAttachmentsModel);
            }
            for (FeedPhotoAttachment feedPhotoAttachment : feed.getPhotoAttachmentList()) {
                photoAttachmentsModel = mapper.map(feedPhotoAttachment, PhotoAttachmentsModel.class);
                photoAttachmentsModel.setFeedId(feed.getPostId());
                attachmentsRepository.save(photoAttachmentsModel);
            }
            for (FeedVideoAttachment feedVideoAttachment : feed.getVideoAttachmentList()) {
                videoAttachmentsModel = mapper.map(feedVideoAttachment, VideoAttachmentsModel.class);
                videoAttachmentsModel.setFeedId(feed.getPostId());
                attachmentsRepository.save(videoAttachmentsModel);
            }
            for (FeedAudioAttachment feedAudioAttachment : feed.getAudioAttachmentList()) {
                audioAttachmentsModel = mapper.map(feedAudioAttachment, AudioAttachmentsModel.class);
                audioAttachmentsModel.setFeedId(feed.getPostId());
                attachmentsRepository.save(audioAttachmentsModel);
            }
        }

    }
}
