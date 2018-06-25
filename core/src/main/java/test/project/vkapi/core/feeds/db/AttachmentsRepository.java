package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

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
}
