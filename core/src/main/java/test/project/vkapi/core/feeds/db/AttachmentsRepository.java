package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import test.project.vkapi.core.feeds.db.models.AttachmentsDAO;
import test.project.vkapi.core.feeds.db.models.AudioAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.LinkAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.PhotoAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.VideoAttachmentsModel;

public class AttachmentsRepository {

    private final AttachmentsDAO dao;
    private final ModelMapper mapper;

    @Inject
    public AttachmentsRepository(AttachmentsDAO dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public void save(AudioAttachmentsModel model) {
        dao.insert(model);
    }

    public void save(VideoAttachmentsModel model) {
        dao.insert(model);
    }

    public void save(LinkAttachmentsModel model) {
        dao.insert(model);
    }

    public void save(PhotoAttachmentsModel model) {
        dao.insert(model);
    }
}
