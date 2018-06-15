package test.project.vkapi.core.db.feed;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = LikesDBModel.class, parentColumns = "id", childColumns = "like_id"),
                       @ForeignKey(entity = CommentsDBModel.class, parentColumns = "id", childColumns = "comments_id")})
public class FeedDBModel {

    @PrimaryKey
    private int id;

    private String text;

    @ColumnInfo(name = "like_id")
    private long likeId;

    @ColumnInfo(name = "comments_id")
    private long commentsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getLikeId() {
        return likeId;
    }

    public void setLikeId(long likeId) {
        this.likeId = likeId;
    }

    public long getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(long commentsId) {
        this.commentsId = commentsId;
    }
}
