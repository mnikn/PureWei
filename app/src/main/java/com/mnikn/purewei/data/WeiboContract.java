package com.mnikn.purewei.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboContract {


    public static final String SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "purewei";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);

    public static final String PATH_WEIBO = "weibo";
    public static final String PATH_WEIBO_PICS = "weibo_pics";
    public static final String PATH_WEIBO_COMMENT = "weibo_comment";
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
        public static final String COLUMN_LIKED = "liked";
        public static final String COLUMN_ATTITUDES_COUNT = "attitudes_count";
        public static final String COLUMN_REPORTS_COUNT = "reports_count";
        public static final String COLUMN_COMMENTS_COUNT = "comments_count";
        public static final String COLUMN_CREATED_TIME = "created_time";

        public static Uri buildWeiboUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
        public static Uri buildWeiboUriWithUser(){
            return CONTENT_URI.buildUpon().appendPath("user").build();
        }
        public static Uri buildWeiboUriWithPics(){
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
        public static boolean getLiked(Cursor cursor){
            return cursor.getInt(cursor.getColumnIndex(COLUMN_LIKED)) > 0;
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
    }

    public static class WeiboPicsEntry implements BaseColumns{
        public static final String TABLE_NAME = "weibo_pics";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEIBO_PICS).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_WEIBO_COMMENT;

        public static final String COLUMN_WEIBO_ID = "weibo_id";
        public static final String COLUMN_THUMBNAIL_URL = "thumbnail_url";
        public static final String COLUMN_MIDDLE_URL = "middle_url";
        public static final String COLUMN_LARGE_URL = "large_url";

        public static Uri buildWeiboPicsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getWeiboId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_WEIBO_ID));
        }
        public static String getThumnbnailUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_THUMBNAIL_URL));
        }
        public static String getMiddleUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLE_URL));
        }
        public static String getLargeUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_LARGE_URL));
        }
    }

    public static class WeiboCommentEntry implements BaseColumns{
        public static final String TABLE_NAME = "weibo_detail";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEIBO_COMMENT).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_WEIBO_COMMENT;

        public static final String COLUMN_WEIBO_ID = "weibo_id";
        public static final String COLUMN_COMMENT_ID = "comment_id";
        public static final String COLUMN_COMMENT_USER_ID = "comment_user_id";
        public static final String COLUMN_COMMENT_TEXT = "comment_text";
        public static final String COLUMN_COMMENT_SOURCE = "comment_source";
        public static final String COLUMN_COMMENT_TIME = "comment_time";

        public static Uri buildWeiboCommentUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static Uri buildWeiboCommentWithUserUri(){
            return CONTENT_URI.buildUpon().appendPath("user").build();
        }

        public static long getWeiboId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_WEIBO_ID));
        }
        public static long getUserId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_COMMENT_USER_ID));
        }
        public static long getCommentId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_COMMENT_ID));
        }
        public static long getCommentUserId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_COMMENT_USER_ID));
        }
        public static long getCommentTime(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_COMMENT_TIME));
        }
        public static String getCommentText(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT_TEXT));
        }
        public static String getCommentSource(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT_SOURCE));
        }

    }

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "user";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        /**
         * 0:normal user 1:account
         */
        public static final String COLUMN_USER_TYPE = "user_type";

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_FOLLOWING = "following";
        public static final String COLUMN_FOLLOW_ME = "follow_me";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FOLLOWERS_COUNT = "followers_count";
        public static final String COLUMN_WEIBO_COUNT = "weibo_count";
        public static final String COLUMN_FRIENDS_COUNT = "friends_count";
        public static final String COLUMN_PROFILE_IMAGE_URL = "profile_image_url";
        public static final String COLUMN_AVATAR_LARGE_URL = "avatar_large_url";
        public static final String COLUMN_AVATAR_HD_URL = "avatar_hd_url";
        public static final String COLUMN_COVER_URL = "cover_url";

        public static Uri buildUserUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
        public static int getUserType(Cursor cursor){
            return cursor.getInt(cursor.getColumnIndex(COLUMN_USER_TYPE));
        }
        public static String getName(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        }
        public static boolean getFollowing(Cursor cursor){
            return cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOWING)) > 0;
        }
        public static boolean getFollowMe(Cursor cursor){
            return cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOW_ME)) > 0;
        }
        public static String getDescription(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        }
        public static String getProfileImageUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_IMAGE_URL));
        }
        public static String getAvatarLargeUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_AVATAR_LARGE_URL));
        }
        public static String getAvatarHdUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_AVATAR_HD_URL));
        }
        public static String getCoverUrl(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_COVER_URL));
        }
        public static long getUserId(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID));
        }
        public static long getFollowerCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_FOLLOWERS_COUNT));
        }
        public static long getWeiboCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_WEIBO_COUNT));
        }
        public static long getFriendsCount(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_FRIENDS_COUNT));
        }

    }

    public static class AccountEntry implements BaseColumns{
        public static final String TABLE_NAME = "account";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ACCOUNT).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + CONTENT_AUTHORITY + "/" + PATH_ACCOUNT;

        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_ACCESS_TOKEN = "access_token";
        public static final String COLUMN_REFRESH_TOKEN = "refresh_token";
        public static final String COLUMN_EXPIRES_TIME = "expires_time";
        public static final String COLUMN_EXPIRES_IN = "expires_in";

        public static Uri buildAccountUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static long getUid(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_UID));
        }
        public static String getAccessToken(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_ACCESS_TOKEN));
        }
        public static String getRefreshToken(Cursor cursor){
            return cursor.getString(cursor.getColumnIndex(COLUMN_REFRESH_TOKEN));
        }
        public static long getExpiresTime(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_EXPIRES_TIME));
        }
        public static long getExpiresIn(Cursor cursor){
            return cursor.getLong(cursor.getColumnIndex(COLUMN_EXPIRES_IN));
        }


    }
}
