package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.api.FeedPageData;
import test.project.vkapi.core.feeds.api.models.PostSource;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.core.feeds.models.FeedVideoAttachment;

public class LocalFeedRepository implements /*FeedRepository,*/ FeedStorage {

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
/*
    @Override
    public Observable<FeedPageData> getFeed(String token, String from, int rows) {
        return dao.getAll().map(new Function<List<FeedDBModel>, List<Feed>>() {
            @Override
            public List<Feed> apply(List<FeedDBModel> feedDBModels) throws Exception {
                final List<Feed> feeds = new ArrayList<>();
                for (FeedDBModel dbModel : feedDBModels) {
                    final Feed feed = mapper.map(dbModel, Feed.class);
                    attachmentsRepository
                            .getPhotoByFeedId(feed.getId())
                            .subscribe(new Consumer<List<FeedPhotoAttachment>>() {
                                @Override
                                public void accept(List<FeedPhotoAttachment> photos) throws Exception {
                                    feed.setPhotoAttachmentList(photos);
                                }
                            });
                    attachmentsRepository
                            .getLinkByFeedId(feed.getId())
                            .subscribe(new Consumer<List<FeedLinkAttachment>>() {
                                @Override
                                public void accept(List<FeedLinkAttachment> links) throws Exception {
                                    feed.setLinkAttachmentList(links);
                                }
                            });
                    attachmentsRepository
                            .getAudioByFeedId(feed.getId())
                            .subscribe(new Consumer<List<FeedAudioAttachment>>() {
                                @Override
                                public void accept(List<FeedAudioAttachment> audio) throws Exception {
                                    feed.setAudioAttachmentList(audio);
                                }
                            });
                    attachmentsRepository
                            .getVideoByFeedId(feed.getId())
                            .subscribe(new Consumer<List<FeedVideoAttachment>>() {
                                @Override
                                public void accept(List<FeedVideoAttachment> video) throws Exception {
                                    feed.setVideoAttachmentList(video);
                                }
                            });

                    postSourceRepository
                            .getById(dbModel.getPost())
                            .subscribe(new Consumer<PostSource>() {
                                @Override
                                public void accept(PostSource postInfoSource) throws Exception {
                                    feed.setSource(postInfoSource);
                                }
                            });

                    feeds.add(feed);
                }
                return feeds;
            }
        }).toObservable();
    }*/

    @Override
    public void save(List<Feed> feeds) {
        FeedDBModel feedDBModel;
        for (Feed feed : feeds) {
            feedDBModel = mapper.map(feed, FeedDBModel.class);
            postSourceRepository.save(feed.getSource());
            feedDBModel.setPost(feed.getSource().getId());
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
