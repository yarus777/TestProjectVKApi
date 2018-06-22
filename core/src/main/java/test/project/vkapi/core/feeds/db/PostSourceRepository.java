package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import test.project.vkapi.core.feeds.db.models.PostSourceDAO;
import test.project.vkapi.core.feeds.db.models.PostSourceModel;

public class PostSourceRepository {

    private final PostSourceDAO dao;
    private final ModelMapper mapper;

    @Inject
    public PostSourceRepository(PostSourceDAO dao, ModelMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }

    public void save(PostSourceModel model) {
        dao.insert(model);
    }
}
