package test.project.vkapi.core.feeds.api;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import test.project.vkapi.core.api.VkApi;
import test.project.vkapi.core.feeds.FeedRepository;
import test.project.vkapi.core.feeds.FeedStorage;
import test.project.vkapi.core.feeds.api.models.FeedItem;
import test.project.vkapi.core.feeds.api.models.FeedList;
import test.project.vkapi.core.feeds.api.models.FeedResponse;
import test.project.vkapi.core.feeds.api.models.PostSource;
import test.project.vkapi.core.feeds.api.models.attachments.AudioItem;
import test.project.vkapi.core.feeds.api.models.attachments.LinkItem;
import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.api.models.attachments.SizesItem;
import test.project.vkapi.core.feeds.api.models.attachments.VideoItem;
import test.project.vkapi.core.feeds.models.Feed;
import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.core.feeds.models.FeedVideoAttachment;
import test.project.vkapi.core.user.UserManager;

public class ApiFeedRepository implements FeedRepository {


    private final VkApi api;
    private final ModelMapper mapper;

    //private final FeedRepository dbRepository;
    private final FeedStorage feedStorage;
    private final UserManager userManager;

    @Inject
    public ApiFeedRepository(VkApi api, ModelMapper mapper, /*@Named("DB") FeedRepository localDbRepository,*/ FeedStorage feedStorage, UserManager userManager) {
        this.api = api;
        this.mapper = mapper;
        //this.dbRepository = localDbRepository;
        this.feedStorage = feedStorage;
        this.userManager = userManager;
    }

    @Override
    public Observable<FeedPageData> getFeed(String token, String startFrom, int rows) {
        return api
                .getFeed(token, "5.77", startFrom, rows, "post")
                .subscribeOn(Schedulers.io())
                .map(new Function<FeedResponse, FeedList>() {
                    @Override
                    public FeedList apply(FeedResponse feedResponse) throws Exception {
                        return feedResponse.getFeedList();
                    }
                })
                .map(new Function<FeedList, FeedPageData>() {
                    @Override
                    public FeedPageData apply(FeedList feedList) throws Exception {
                        List<Feed> feeds = new ArrayList<>();
                        List<FeedItem> responseFeeds = feedList.getItems();
                        HashMap<String, PostSource> sourceHashMap = feedList.getInfoItems();

                        Feed feed;

                        for (FeedItem feedItem : responseFeeds) {

                            List<FeedAudioAttachment> audioList = new ArrayList<>();
                            List<AudioItem> audioItems = feedItem.getAudioAttachments();
                            for (AudioItem audioItem : audioItems) {
                                audioList.add(mapper.map(audioItem, FeedAudioAttachment.class));
                            }

                            List<FeedLinkAttachment> linkList = new ArrayList<>();
                            List<LinkItem> linkItems = feedItem.getLinkAttachments();
                            for (LinkItem linkItem : linkItems) {
                                linkList.add(mapper.map(linkItem, FeedLinkAttachment.class));
                            }

                            List<FeedVideoAttachment> videoList = new ArrayList<>();
                            List<VideoItem> videoItems = feedItem.getVideoAttachments();
                            for (VideoItem videoItem : videoItems) {
                                videoList.add(mapper.map(videoItem, FeedVideoAttachment.class));
                            }

                            List<FeedPhotoAttachment> photoList = new ArrayList<>();
                            List<PhotoItem> photoItems = feedItem.getPhotoAttachments();
                            for (PhotoItem photoItem : photoItems) {
                                SizesItem sizesItem = photoItem.chooseBiggestPhotoItem();
                                photoList.add(new FeedPhotoAttachment(photoItem.getId(), sizesItem.getUrl(), sizesItem.getWidth(), sizesItem.getHeight()));
                            }
                            feed = mapper.map(feedItem, Feed.class);
                            feed.setAudioAttachmentList(audioList);
                            feed.setPhotoAttachmentList(photoList);
                            feed.setLinkAttachmentList(linkList);
                            feed.setVideoAttachmentList(videoList);
                            feed.setSource(sourceHashMap.get(feedItem.getSourceId()));
                            feeds.add(feed);
                        }
                        return new FeedPageData(feeds, feedList.nextFrom);
                    }
                })
                .doOnSuccess(new Consumer<FeedPageData>() {
                    @Override
                    public void accept(FeedPageData feedsData) throws Exception {
                        feedStorage.save(feedsData.feeds);
                    }
                })
                .toObservable()
                //.mergeWith(dbRepository.getFeed())
                .distinct();
    }

}
