package test.project.vkapi.core.user.db.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users")
    Flowable<List<UserDBModel>> getAll();

    @Query("SELECT * FROM users WHERE id = :id")
    Single<UserDBModel> getById(int id);

}
