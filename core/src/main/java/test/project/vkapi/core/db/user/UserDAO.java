package test.project.vkapi.core.db.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users")
    Flowable<List<UserDBModel>> getAll();

    @Query("SELECT * FROM users WHERE id = :id")
    Flowable<UserDBModel> getById(int id);

}
