package test.project.vkapi.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import test.project.vkapi.core.feeds.db.models.AudioAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.FeedDAO;
import test.project.vkapi.core.feeds.db.models.FeedDBModel;
import test.project.vkapi.core.feeds.db.models.LinkAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.PhotoAttachmentsModel;
import test.project.vkapi.core.feeds.db.models.VideoAttachmentsModel;
import test.project.vkapi.core.user.db.models.UserDAO;
import test.project.vkapi.core.user.db.models.UserDBModel;

@Database(entities = {UserDBModel.class, FeedDBModel.class, PhotoAttachmentsModel.class, AudioAttachmentsModel.class, LinkAttachmentsModel.class, VideoAttachmentsModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract FeedDAO feedDAO();
}
