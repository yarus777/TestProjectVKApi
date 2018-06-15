package test.project.vkapi.core.db;

import org.modelmapper.ModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import test.project.vkapi.core.User;
import test.project.vkapi.core.api.feed.FeedResponse;
import test.project.vkapi.core.api.user.UsersResponse;
import test.project.vkapi.core.db.user.UserDBModel;

public class DBRepository implements IAppRepository {

    AppDatabase appDatabase;
    ModelMapper mapper;

    @Inject
    public DBRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        mapper = new ModelMapper();
    }

    @Override
    public Observable<FeedResponse> getFeed() {
        return null;
    }

    @Override
    public Observable<User> getUsers() {
        return appDatabase.userDAO().getById(0).map(new Function<UserDBModel, User>() {
            @Override
            public User apply(UserDBModel userDBModel) throws Exception {
                return mapper.map(userDBModel, User.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).toObservable();

    }
}
