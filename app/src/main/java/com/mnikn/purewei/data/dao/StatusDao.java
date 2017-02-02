package com.mnikn.purewei.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.mnikn.library.utils.DateUtils;
import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.model.Status;
import com.mnikn.purewei.support.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class StatusDao {


    public static List<Status> getStatuses() {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.StatusEntry.buildStatusUriWithUser(),
                null,
                null,
                null,
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
        List<Status> statuses = new ArrayList<>();
        while (cursor.moveToNext()) {
            statuses.add(getStatus(cursor));
        }
        return statuses;
    }

    public static Status getStatus(long statusId) {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.StatusEntry.buildStatusUriWithUser(),
                null,
                WeiboContract.StatusEntry.COLUMN_STATUS_ID + " = ?",
                new String[]{Numbers.longToString(statusId)},
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
        cursor.moveToFirst();

        return getStatus(cursor);
    }

    public static Status getStatus(Cursor cursor) {
        Status status = new Status();
        status.id = WeiboContract.StatusEntry.getStatusId(cursor);
        status.user = UserDao.getUser(WeiboContract.StatusEntry.getUserId(cursor));
        long reweetId = WeiboContract.StatusEntry.getRetweetId(cursor);
        if(reweetId != 0){
            status.retweetStatus = StatusDao.getStatus(reweetId);
        }
        status.createdAt = DateUtils.longToDate(WeiboContract.StatusEntry.getCreatedTime(cursor));
        status.text = WeiboContract.StatusEntry.getText(cursor);
        status.source = WeiboContract.StatusEntry.getSource(cursor);
        status.repostsCount = WeiboContract.StatusEntry.getReportsCount(cursor);
        status.attitudesCount = WeiboContract.StatusEntry.getAttitudesCount(cursor);
        status.commentsCount = WeiboContract.StatusEntry.getCommentsCount(cursor);
        status.liked = WeiboContract.StatusEntry.getLiked(cursor);
        status.pictures = PictureDao.getPictures(WeiboContract.StatusEntry.getStatusId(cursor));
        return status;
    }

    public static void insertStatus(ContentValues values) {
        App.getAppContext().getContentResolver()
                .insert(WeiboContract.StatusEntry.CONTENT_URI, values);
    }

    public static void insertStatus(Status status) {
        insertStatus(toContentValues(status));
    }

    public static void insertStatuses(List<Status> statuses) {
        ContentValues[] values = new ContentValues[statuses.size()];
        for (int i = 0; i < statuses.size(); ++i) {
            values[i] = toContentValues(statuses.get(i));
        }
        App.getAppContext().getContentResolver()
                .bulkInsert(WeiboContract.StatusEntry.CONTENT_URI, values);
    }

    public static void delete(long statusId) {
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.StatusEntry.CONTENT_URI,
                        WeiboContract.StatusEntry.COLUMN_STATUS_ID + " = ?",
                        new String[]{Numbers.longToString(statusId)});
    }

    public static void clear() {
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.StatusEntry.CONTENT_URI,
                        null,
                        null);
    }

    private static ContentValues toContentValues(Status status) {
        ContentValues values = new ContentValues();
        values.put(WeiboContract.StatusEntry.COLUMN_STATUS_ID, status.id);
        values.put(WeiboContract.StatusEntry.COLUMN_USER_ID, status.user.id);
        if(status.retweetStatus != null){
            values.put(WeiboContract.StatusEntry.COLUMN_RETWEET_ID, status.retweetStatus.id);
        }
        values.put(WeiboContract.StatusEntry.COLUMN_CREATED_TIME, status.createdAt);
        values.put(WeiboContract.StatusEntry.COLUMN_TEXT, status.text);
        values.put(WeiboContract.StatusEntry.COLUMN_SOURCE, TextUtil.cutHerfInfo(status.source));
        values.put(WeiboContract.StatusEntry.COLUMN_REPORTS_COUNT, status.repostsCount);
        values.put(WeiboContract.StatusEntry.COLUMN_ATTITUDES_COUNT, status.attitudesCount);
        values.put(WeiboContract.StatusEntry.COLUMN_COMMENTS_COUNT, status.commentsCount);
        values.put(WeiboContract.StatusEntry.COLUMN_LIKED, status.liked);
        return values;
    }
}
