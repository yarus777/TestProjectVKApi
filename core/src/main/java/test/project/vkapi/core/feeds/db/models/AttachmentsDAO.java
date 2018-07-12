package test.project.vkapi.core.feeds.db.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AttachmentsDAO {

    @Query("SELECT * FROM audios")
    Flowable<List<AudioAttachmentsModel>> getAllAudioAttachments();

    @Query("SELECT * FROM audios WHERE feed_id = :id")
    Single<List<AudioAttachmentsModel>> getAudioAttachmentByFeedId(String id);

    @Query("SELECT * FROM videos")
    Flowable<List<VideoAttachmentsModel>> getAllVideoAttachments();

    @Query("SELECT * FROM videos WHERE feed_id = :id")
    Single<List<VideoAttachmentsModel>> getVideoAttachmentByFeedId(String id);

    @Query("SELECT * FROM links")
    Flowable<List<LinkAttachmentsModel>> getAllLinkAttachments();

    @Query("SELECT * FROM links WHERE feed_id = :id")
    Single<List<LinkAttachmentsModel>> getLinkAttachmentByFeedId(String id);

    @Query("SELECT * FROM photos")
    Flowable<List<PhotoAttachmentsModel>> getAllPhotoAttachments();

    @Query("SELECT * FROM photos WHERE feed_id = :id")
    Single<List<PhotoAttachmentsModel>> getPhotoAttachmentByFeedId(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AudioAttachmentsModel model);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(VideoAttachmentsModel model);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LinkAttachmentsModel model);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PhotoAttachmentsModel model);

}

