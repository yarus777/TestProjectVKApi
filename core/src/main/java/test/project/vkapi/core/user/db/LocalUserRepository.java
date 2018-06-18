package test.project.vkapi.core.user.db;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import test.project.vkapi.core.user.User;
import test.project.vkapi.core.user.UserStorage;
import test.project.vkapi.core.user.db.models.UserDAO;
import test.project.vkapi.core.user.db.models.UserDBModel;
import test.project.vkapi.core.user.UserRepository;

public class LocalUserRepository implements UserRepository, UserStorage {
    private final UserDAO dao;
    private final ModelMapper mapper;

    @Inject
    LocalUserRepository(UserDAO userDao, ModelMapper mapper) {
        this.dao = userDao;
        this.mapper = mapper;
    }

    @Override
    public Observable<User> getUser() {
        return dao.getById(0).map(new Function<UserDBModel, User>() {
            @Override
            public User apply(UserDBModel userDBModel) throws Exception {
                return mapper.map(userDBModel, User.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).toObservable();

    }

    @Override
    public void saveUser(User user) {
        //dao.insert(user);??
    }
}
