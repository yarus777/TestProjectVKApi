package test.project.vkapi.core.feeds.db.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PostSourceDAO {

    @Query("SELECT * FROM postSources")
    Flowable<List<PostSourceModel>> getAllPostSource();

    @Query("SELECT * FROM postSources WHERE id = :id")
    Single<PostSourceModel> getPostSourceById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PostSourceModel model);
}