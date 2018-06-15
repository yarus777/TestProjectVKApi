package test.project.vkapi.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import test.project.vkapi.core.db.feed.CommentsDBModel;
import test.project.vkapi.core.db.feed.FeedDAO;
import test.project.vkapi.core.db.feed.FeedDBModel;
import test.project.vkapi.core.db.feed.LikesDBModel;
import test.project.vkapi.core.db.user.UserDAO;
import test.project.vkapi.core.db.user.UserDBModel;

@Database(entities = {UserDBModel.class, FeedDBModel.class, LikesDBModel.class, CommentsDBModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract FeedDAO feedDAO();
}
