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

    @Query("SELECT * FROM audios WHERE id = :id")
    Single<AudioAttachmentsModel> getAudioAttachmentById(int id);

    @Query("SELECT * FROM videos")
    Flowable<List<VideoAttachmentsModel>> getAllVideoAttachments();

    @Query("SELECT * FROM videos WHERE id = :id")
    Single<VideoAttachmentsModel> getVideoAttachmentById(int id);

    @Query("SELECT * FROM links")
    Flowable<List<LinkAttachmentsModel>> getAllLinkAttachments();

    @Query("SELECT * FROM links WHERE id = :id")
    Single<LinkAttachmentsModel> getLinkAttachmentById(int id);

    @Query("SELECT * FROM photos")
    Flowable<List<PhotoAttachmentsModel>> getAllPhotoAttachments();

    @Query("SELECT * FROM photos WHERE id = :id")
    Single<PhotoAttachmentsModel> getPhotoAttachmentById(int id);

    @Insert
    void insert(AudioAttachmentsModel model);

    @Insert
    void insert(VideoAttachmentsModel model);

    @Insert
    void insert(LinkAttachmentsModel model);

    @Insert
    void insert(PhotoAttachmentsModel model);

}

