package test.project.vkapi.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import test.project.vkapi.core.feeds.db.models.CommentsDBModel;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.db.models.LikesDBModel;
import test.project.vkapi.core.user.db.models.UserDAO;
import test.project.vkapi.core.user.db.models.UserDBModel;

@Database(entities = {UserDBModel.class, FeedDBModel.class, LikesDBModel.class, CommentsDBModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract FeedDAO feedDAO();
}
