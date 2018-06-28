package test.project.vkapi.core.feeds.db;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import test.project.vkapi.core.feeds.api.models.GroupItem;
import test.project.vkapi.core.feeds.api.models.PostInfoSource;
import test.project.vkapi.core.feeds.api.models.PostSource;
import test.project.vkapi.core.feeds.api.models.ProfileItem;
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

    public void save(PostSource model) {
        dao.insert(mapper.map(model, PostSourceModel.class));
    }

    public Single<PostSource> getById(String id) {
        return dao
                .getPostSourceById(id)
                .map(new Function<PostSourceModel, PostSource>() {
                    @Override
                    public PostSource apply(PostSourceModel postSourceModel) throws Exception {
                            return mapper.map(postSourceModel, PostSource.class);
                    }
                });
    }
}
