package test.project.vkapi.core.api.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import test.project.vkapi.core.api.feed.FeedItem;

@Database(entities = {FeedItem.class}, version = 1)
public abstract  class FeedDatabase extends RoomDatabase {
    public abstract FeedItemDao getFeedItemDao();
}
