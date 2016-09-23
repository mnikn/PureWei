package com.mnikn.purewei.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class WeiboContract {


    public static final String SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "purewei";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);

    public static final String PATH_WEIBO = "weibo";
    public static final String PATH_WEIBO_DETAIL = "weibo_detail";
    public static final String PATH_USER = "user";
    public static final String PATH_ACCOUNT = "account";

    public static class WeiboEntry implements BaseColumns{
        public static final String TABLE_NAME = "weibo";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEIBO).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_WEIBO;

        public static final String COLUMN_WEIBO_ID = "weibo_id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_RETWEET_ID = "retweet_id";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_SOURCE = "source";
        public static final String COLUMN_ATTITUDES_COUNT = "attitudes_count";
        public static final String COLUMN_REPORTS_COUNT = "reports_count";
        public static final String COLUMN_COMMENTS_COUNT = "comments_count";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_RETWEET_TEXT = "retweet_text";
        public static final String COLUMN_RETWEET_USER_NAME = "retweet_user_name";

        public static Uri buildWeiboUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
        public static Uri buildWeiboUriWithUser(){
            return CONTENT_URI.buildUpon().appendPath("user").build();
        }
        public static Uri buildWeiboUriWithDetail(){
            return CONTENT_URI.buildUpon().appendPath("detail").build();
        }

        public static long getWeiboId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_WEIBO_ID));
        }
        public static long getUserId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        }
        public static long getRetweetId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_RETWEET_ID));
        }
        public static String getText(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_TEXT));
        }
        public static long getCreatedTime(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_CREATED_TIME));
        }
        public static String getSource(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE));
        }
        public static long getAttitudesCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_ATTITUDES_COUNT));
        }
        public static long getReportsCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_REPORTS_COUNT));
        }
        public static long getCommentsCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_COMMENTS_COUNT));
        }
        public static String getRetweetText(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_RETWEET_TEXT));
        }
        public static String getRetweetUserName(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_RETWEET_USER_NAME));
        }
    }

    public static class WeiboDetailEntry implements BaseColumns{
        public static final String TABLE_NAME = "weibo_detail";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEIBO_DETAIL).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_WEIBO_DETAIL;

        public static final String COLUMN_WEIBO_ID = "weibo_id";
        public static final String COLUMN_PICS_URL = "pics_url";

        public static Uri buildWeiboDetailUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static long getWeiboId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_WEIBO_ID));
        }
        public static String getPicsUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_PICS_URL));
        }
    }

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "user";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FOLLOWERS_COUNT = "followers_count";
        public static final String COLUMN_PROFILE_IMAGE_URL = "profile_image_url";

        public static Uri buildUserUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }


        public static String getName(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        }
        public static String getDescription(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        }
        public static String getProfileImageUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_IMAGE_URL));
        }
        public static long getUsertId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        }
        public static long getFollowerCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_FOLLOWERS_COUNT));
        }
    }

    public static class AccountEntry implements BaseColumns{
        public static final String TABLE_NAME = "account";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ACCOUNT).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_ACCOUNT;

        public static final String COLUMN_TOKEN = "token";
        public static final String COLUMN_UID = "uid";

        public static Uri buildAccountUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static String getToken(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN));
        }
        public static long getUid(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_UID));
        }
    }

    public static class CommentEntry{
        public static final String TABLE_NAME = "comment";
    }
}
