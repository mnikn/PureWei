package com.mnikn.purewei.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mnikn.purewei.data.WeiboContract.*;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class WeiboDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "weibo.db";
    private static final int DB_VERSION = 3;

    public WeiboDbHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE_WEIBO = "CREATE TABLE " +
                WeiboEntry.TABLE_NAME + " (" +
                WeiboEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WeiboEntry.COLUMN_WEIBO_ID + " INTEGER NOT NULL, " +
                WeiboEntry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                WeiboEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                WeiboEntry.COLUMN_SOURCE + " TEXT NOT NULL, " +
                WeiboEntry.COLUMN_RETWEET_ID + " INTEGER, " +
                WeiboEntry.COLUMN_RETWEET_TEXT + " TEXT, " +
                WeiboEntry.COLUMN_RETWEET_USER_NAME + " TEXT," +
                WeiboEntry.COLUMN_ATTITUDES_COUNT + " INTEGER NOT NULL, " +
                WeiboEntry.COLUMN_COMMENTS_COUNT + " INTEGER NOT NULL, " +
                WeiboEntry.COLUMN_REPORTS_COUNT + " INTEGER NOT NULL, " +
                WeiboEntry.COLUMN_CREATED_TIME + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + WeiboEntry.COLUMN_USER_ID + ") REFERENCES " +
                UserEntry.TABLE_NAME + " (" + UserEntry.COLUMN_USER_ID + "))";

        final String SQL_CREATE_TABLE_USER = "CREATE TABLE " +
                UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserEntry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                UserEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                UserEntry.COLUMN_PROFILE_IMAGE_URL + " TEXT NOT NULL, " +
                UserEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                UserEntry.COLUMN_FOLLOWERS_COUNT + " INTEGER NOT NULL " + ")";

        final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE " +
                AccountEntry.TABLE_NAME + " (" +
                AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AccountEntry.COLUMN_TOKEN + " TEXT NOT NULL, " +
                AccountEntry.COLUMN_UID + " INTEGER NOT NULL" + ")";

        db.execSQL(SQL_CREATE_TABLE_WEIBO);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WeiboEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AccountEntry.TABLE_NAME);
    }


}
