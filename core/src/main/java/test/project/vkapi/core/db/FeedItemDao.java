package test.project.vkapi.core.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import test.project.vkapi.core.api.feed.FeedItem;


/*@Dao
public interface FeedItemDao {

    @Query("SELECT * FROM feedItem")
    List<FeedItem> getAll();

    @Query("SELECT * FROM feedItem WHERE feedId = :id")
    FeedItem getById(String id);

    @Insert
    void insert(FeedItem feedItem);

    @Update
    void update(FeedItem feedItem);

    @Delete
    void delete(FeedItem feedItem);
}*/
