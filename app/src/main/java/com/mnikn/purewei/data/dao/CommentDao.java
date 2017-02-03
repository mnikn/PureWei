package com.mnikn.purewei.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.mnikn.library.utils.DateUtils;
import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentDao {

    public static List<Comment> getComments(long uid) {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.CommentEntry.buildCommentWithUserUri(),
                null,
                WeiboContract.UserEntry.TABLE_NAME + "." + WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{Numbers.longToString(uid)},
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
        List<Comment> comments = new ArrayList<>();
        while (cursor.moveToNext()) {
            comments.add(getComment(cursor));
        }
        return comments;
    }

    public static Comment getComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.id = WeiboContract.CommentEntry.getCommentId(cursor);
        comment.status = StatusDao.getStatus(WeiboContract.CommentEntry.getStatusId(cursor));
        comment.user = UserDao.getUser(WeiboContract.CommentEntry.getUserId(cursor));
        comment.text = WeiboContract.CommentEntry.getCommentText(cursor);
        comment.source = WeiboContract.CommentEntry.getCommentSource(cursor);
        comment.createdAt = DateUtils.longToDate(WeiboContract.CommentEntry.getCommentTime(cursor));
        return comment;
    }

    public static void insertComment(ContentValues values) {
        App.getAppContext().getContentResolver()
                .insert(WeiboContract.CommentEntry.CONTENT_URI, values);
    }

    public static void insertComment(Comment comment) {
        insertComment(toContentValues(comment));
    }

    public static void insertComments(List<Comment> comments) {
        ContentValues[] values = new ContentValues[comments.size()];
        for (int i = 0;i < comments.size();++i) {
            values[i] = toContentValues(comments.get(i));
        }
        App.getAppContext().getContentResolver()
                .bulkInsert(WeiboContract.CommentEntry.CONTENT_URI,values);
    }

    private static ContentValues toContentValues(Comment comment){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.CommentEntry._ID, comment.id);
        values.put(WeiboContract.CommentEntry.COLUMN_STATUS_ID, comment.id);
        values.put(WeiboContract.CommentEntry.COLUMN_COMMENT_USER_ID, comment.status.user.id);
        values.put(WeiboContract.CommentEntry.COLUMN_COMMENT_TEXT, comment.text);
        values.put(WeiboContract.CommentEntry.COLUMN_COMMENT_SOURCE, comment.source);
        values.put(WeiboContract.CommentEntry.COLUMN_COMMENT_TIME, comment.createdAt);
        return values;
    }

    public static void delete(long statusId){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.CommentEntry.CONTENT_URI,
                        WeiboContract.CommentEntry.COLUMN_STATUS_ID + " = ?",
                        new String[]{Numbers.longToString(statusId)});
    }

    public static void clear(){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.CommentEntry.CONTENT_URI,
                        null,
                        null);
    }
}
