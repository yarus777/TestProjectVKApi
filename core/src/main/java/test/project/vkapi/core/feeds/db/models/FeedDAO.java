package test.project.vkapi.core.feeds.db.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface FeedDAO {

    @Query("SELECT * FROM feeds")
    Flowable<List<FeedDBModel>> getAll();

    @Query("SELECT * FROM feeds WHERE id = :id")
    Single<FeedDBModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FeedDBModel model);

}
