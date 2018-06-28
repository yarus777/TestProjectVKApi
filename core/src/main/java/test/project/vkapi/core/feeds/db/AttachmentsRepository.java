package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.db.models.AttachmentsDAO;
import test.project.vkapi.core.feeds.db.models.AudioAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.LinkAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.PhotoAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.VideoAttachmentsModel;
import test.project.vkapi.core.feeds.models.FeedAudioAttachment;
import test.project.vkapi.core.feeds.models.FeedLinkAttachment;
import test.project.vkapi.core.feeds.models.FeedPhotoAttachment;
import test.project.vkapi.core.feeds.models.FeedVideoAttachment;

public class AttachmentsRepository {

    private final AttachmentsDAO dao;
    private final ModelMapper mapper;

    @Inject
    public AttachmentsRepository(AttachmentsDAO dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public void save(String feedId, FeedAudioAttachment model) {
        AudioAttachmentsModel dbModel = mapper.map(model, AudioAttachmentsModel.class);
        dbModel.setFeedId(feedId);
        dao.insert(dbModel);
    }

    public void save(String feedId, FeedVideoAttachment model) {
        VideoAttachmentsModel dbModel = mapper.map(model, VideoAttachmentsModel.class);
        dbModel.setFeedId(feedId);
        dao.insert(dbModel);
    }

    public void save(String feedId, FeedLinkAttachment model) {
        LinkAttachmentsModel dbModel = mapper.map(model, LinkAttachmentsModel.class);
        dbModel.setFeedId(feedId);
        dao.insert(dbModel);
    }

    public void save(String feedId, FeedPhotoAttachment model) {
        PhotoAttachmentsModel dbModel = mapper.map(model, PhotoAttachmentsModel.class);
        dbModel.setFeedId(feedId);
        dao.insert(dbModel);
    }

    public Single<List<FeedPhotoAttachment>> getPhotoByFeedId(String id) {
        return dao
                .getPhotoAttachmentByFeedId(id)
                .toObservable()
                .flatMap(new Function<List<PhotoAttachmentsModel>, Observable<PhotoAttachmentsModel>>() {
                    @Override
                    public Observable<PhotoAttachmentsModel> apply(List<PhotoAttachmentsModel> list) throws Exception {
                        return Observable.fromIterable(list);
                    }
                })
                .map(new Function<PhotoAttachmentsModel, FeedPhotoAttachment>() {
                    @Override
                    public FeedPhotoAttachment apply(PhotoAttachmentsModel model) throws Exception {
                        return mapper.map(model, FeedPhotoAttachment.class);
                    }
                })
                .toList();
    }

    public Single<List<FeedLinkAttachment>> getLinkByFeedId(String id) {
        return dao
                .getLinkAttachmentByFeedId(id)
                .toObservable()
                .flatMap(new Function<List<LinkAttachmentsModel>, Observable<LinkAttachmentsModel>>() {
                    @Override
                    public Observable<LinkAttachmentsModel> apply(List<LinkAttachmentsModel> list) throws Exception {
                        return Observable.fromIterable(list);
                    }
                })
                .map(new Function<LinkAttachmentsModel, FeedLinkAttachment>() {
                    @Override
                    public FeedLinkAttachment apply(LinkAttachmentsModel model) throws Exception {
                        return mapper.map(model, FeedLinkAttachment.class);
                    }
                })
                .toList();
    }

    public Single<List<VideoAttachmentsModel>> getVideoByFeedId(String id) {
        return dao.getVideoAttachmentByFeedId(id);
    }

    public Single<List<FeedAudioAttachment>> getAudioByFeedId(String id) {
        return dao
                .getAudioAttachmentByFeedId(id)
                .toObservable()
                .flatMap(new Function<List<AudioAttachmentsModel>, Observable<AudioAttachmentsModel>>() {
                    @Override
                    public Observable<AudioAttachmentsModel> apply(List<AudioAttachmentsModel> list) throws Exception {
                        return Observable.fromIterable(list);
                    }
                })
                .map(new Function<AudioAttachmentsModel, FeedAudioAttachment>() {
                    @Override
                    public FeedAudioAttachment apply(AudioAttachmentsModel model) throws Exception {
                        return mapper.map(model, FeedAudioAttachment.class);
                    }
                })
                .toList();
    }
}
