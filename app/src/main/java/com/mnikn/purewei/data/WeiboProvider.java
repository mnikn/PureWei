package com.mnikn.purewei.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class WeiboProvider extends ContentProvider {

    private static final int WEIBO = 100;
    private static final int USER = 101;
    private static final int WEIBO_WITH_USER = 102;
    private static UriMatcher sUriMatcher;

    private WeiboDbHelper mDbHelper;
    private ContentResolver mResolver;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO,WEIBO);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_USER,USER);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO + "/*",WEIBO_WITH_USER);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new WeiboDbHelper(getContext());
        if(getContext() != null){
            mResolver = getContext().getContentResolver();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor;
        switch (code){
            case WEIBO:
                cursor = db.query(
                        WeiboContract.WeiboEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case USER:
                cursor = db.query(
                            WeiboContract.UserEntry.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);
                break;
            case WEIBO_WITH_USER:
                String tableName = WeiboContract.WeiboEntry.TABLE_NAME + " INNER JOIN " +
                        WeiboContract.UserEntry.TABLE_NAME +" ON " + WeiboContract.WeiboEntry.TABLE_NAME +
                        "." + WeiboContract.WeiboEntry.COLUMN_USER_ID + " = " + WeiboContract.UserEntry.TABLE_NAME +
                        "." + WeiboContract.UserEntry.ID;
                cursor = db.query(
                            tableName,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);
                break;
            default:
                throw new IllegalArgumentException("No such a uri: " + uri.toString());
        }
        if(cursor != null){
            cursor.setNotificationUri(mResolver,uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int code = sUriMatcher.match(uri);
        String type;
        switch (code){
            case WEIBO:
                type = WeiboContract.WeiboEntry.CONTENT_TYPE;
                break;
            case USER:
                type = WeiboContract.UserEntry.CONTENT_TYPE;
                break;
            default:
                throw new IllegalArgumentException("No such a uri: " + uri.toString());
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int code = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri retUri;
        long rowId;
        switch (code){
            case WEIBO:
                rowId = db.insert(WeiboContract.WeiboEntry.TABLE_NAME,null,values);
                if(rowId > 0){
                    retUri = WeiboContract.WeiboEntry.buildWeiboUri(rowId);
                }
                else{
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            case USER:
                rowId = db.insert(WeiboContract.UserEntry.TABLE_NAME,null,values);
                if(rowId > 0){
                    retUri = WeiboContract.UserEntry.buildUserUri(rowId);
                }
                else{
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new IllegalArgumentException("No such a uri: " + uri.toString());
        }
        mResolver.notifyChange(uri,null);

        return retUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        int code = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int count= 0;
        long rowId;
        switch (code){
            case WEIBO:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.insert(WeiboContract.WeiboEntry.TABLE_NAME,null,contentValues);
                    if(rowId <= 0){
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    }
                    ++count;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            default:
                throw new IllegalArgumentException("No such a uri: " + uri.toString());
        }
        mResolver.notifyChange(uri,null);
        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int code = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowId;
        switch (code){
            case WEIBO:
                rowId = db.delete(WeiboContract.WeiboEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER:
                rowId = db.delete(WeiboContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new android.database.SQLException("No such a uri" + uri);
        }

        mResolver.notifyChange(uri,null);

        return rowId;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int matchCode = sUriMatcher.match(uri);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowId;
        switch (matchCode){
            case WEIBO:
                rowId = db.update(WeiboContract.WeiboEntry.TABLE_NAME, values, selection, selectionArgs);
                if(rowId <= 0){
                    throw new android.database.SQLException("Failed to update row into " + uri);
                }
                break;
            case USER:
                rowId = db.update(WeiboContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);
                if(rowId <= 0){
                    throw new android.database.SQLException("Failed to update row into " + uri);
                }
                break;
            default:
                throw new android.database.SQLException("No such a uri " + uri);
        }
        mResolver.notifyChange(uri,null);

        return rowId;
    }
}
