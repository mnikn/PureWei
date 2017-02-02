package com.mnikn.purewei.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mnikn.purewei.data.WeiboContract.*;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "weibo.db";
    private static final int DB_VERSION = 3;

    public WeiboDbHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE_STATUS = "CREATE TABLE " +
                StatusEntry.TABLE_NAME + " (" +
                StatusEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StatusEntry.COLUMN_STATUS_ID + " INTEGER NOT NULL UNIQUE, " +
                StatusEntry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                StatusEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                StatusEntry.COLUMN_SOURCE + " TEXT NOT NULL, " +
                StatusEntry.COLUMN_LIKED + " BOOLEAN, " +
                StatusEntry.COLUMN_RETWEET_ID + " INTEGER, " +
                StatusEntry.COLUMN_ATTITUDES_COUNT + " INTEGER NOT NULL, " +
                StatusEntry.COLUMN_COMMENTS_COUNT + " INTEGER NOT NULL, " +
                StatusEntry.COLUMN_REPORTS_COUNT + " INTEGER NOT NULL, " +
                StatusEntry.COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + StatusEntry.COLUMN_USER_ID + ") REFERENCES " +
                UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USER_ID + "))";

        final String SQL_CREATE_TABLE_WEIBO_PICS = "CREATE TABLE " +
                PictureEntry.TABLE_NAME + " (" +
                PictureEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PictureEntry.COLUMN_STATUS_ID + " INTEGER NOT NULL, " +
                PictureEntry.COLUMN_THUMBNAIL_URL + " TEXT NOT NULL, " +
                PictureEntry.COLUMN_MIDDLE_URL + " TEXT NOT NULL, " +
                PictureEntry.COLUMN_LARGE_URL + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + PictureEntry.COLUMN_STATUS_ID + ") REFERENCES " +
                StatusEntry.TABLE_NAME + " (" + StatusEntry.COLUMN_STATUS_ID + "))";

        final String SQL_CREATE_TABLE_WEIBO_COMMENT = "CREATE TABLE " +
                CommentEntry.TABLE_NAME + " (" +
                CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CommentEntry.COLUMN_STATUS_ID + " INTEGER NOT NULL, " +
                CommentEntry.COLUMN_COMMENT_ID + " INTEGER NOT NULL, " +
                CommentEntry.COLUMN_COMMENT_USER_ID + " INTEGER NOT NULL, " +
                CommentEntry.COLUMN_COMMENT_TIME + " INTEGER NOT NULL, " +
                CommentEntry.COLUMN_COMMENT_TEXT + " TEXT NOT NULL, " +
                CommentEntry.COLUMN_COMMENT_SOURCE + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + CommentEntry.COLUMN_STATUS_ID + ") REFERENCES " +
                StatusEntry.TABLE_NAME + " (" + StatusEntry.COLUMN_STATUS_ID + "))";

        final String SQL_CREATE_TABLE_USER = "CREATE TABLE " +
                UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserEntry.COLUMN_USER_ID + " INTEGER NOT NULL UNIQUE, " +
                UserEntry.COLUMN_USER_TYPE + " INTEGER NOT NULL, " +
                UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_FOLLOWING + " BOOLEAN NOT NULL, " +
                UserEntry.COLUMN_FOLLOW_ME + " BOOLEAN NOT NULL, " +
                UserEntry.COLUMN_PROFILE_IMAGE_URL + " TEXT NOT NULL, " +
                UserEntry.COLUMN_AVATAR_LARGE_URL + " TEXT NOT NULL, " +
                UserEntry.COLUMN_AVATAR_HD_URL + " TEXT NOT NULL, " +
                UserEntry.COLUMN_COVER_URL + " TEXT, " +
                UserEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                UserEntry.COLUMN_STATUS_COUNT + " TEXT NOT NULL, " +
                UserEntry.COLUMN_FRIENDS_COUNT + " TEXT NOT NULL, " +
                UserEntry.COLUMN_FOLLOWERS_COUNT + " INTEGER NOT NULL " + ")";

        final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE " +
                AccountEntry.TABLE_NAME + " (" +
                AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AccountEntry.COLUMN_ACCESS_TOKEN + " TEXT NOT NULL, " +
                AccountEntry.COLUMN_REFRESH_TOKEN + " TEXT NOT NULL, " +
                AccountEntry.COLUMN_EXPIRES_TIME + " INTEGER NOT NULL, " +
                AccountEntry.COLUMN_EXPIRES_IN + " INTEGER NOT NULL, " +
                AccountEntry.COLUMN_ID + " INTEGER NOT NULL" + ")";

        final String SQL_CREATE_TABLE_DRAFT = "CREATE TABLE " +
                DraftEntry.TABLE_NAME + " (" +
                DraftEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DraftEntry.COLUMN_TYPE + " INTEGER NOT NULL, " +
                DraftEntry.COLUMN_WEIBO_ID + " INTEGER, " +
                DraftEntry.COLUMN_CONTENT + " TEXT NOT NULL" + ")";

        db.execSQL(SQL_CREATE_TABLE_STATUS);
        db.execSQL(SQL_CREATE_TABLE_WEIBO_PICS);
        db.execSQL(SQL_CREATE_TABLE_WEIBO_COMMENT);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_ACCOUNT);
        db.execSQL(SQL_CREATE_TABLE_DRAFT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StatusEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PictureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CommentEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AccountEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DraftEntry.TABLE_NAME);
        onCreate(db);
    }


}
