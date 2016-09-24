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

import com.mnikn.purewei.data.WeiboContract.*;;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboProvider extends ContentProvider {

    private static final int WEIBO = 100;
    private static final int WEIBO_PICS = 101;
    private static final int WEIBO_COMMENT = 102;
    private static final int USER = 103;
    private static final int ACCOUNT = 104;
    private static final int WEIBO_WITH_USER = 105;
    private static final int WEIBO_WITH_COMMENT = 106;
    private static UriMatcher sUriMatcher;

    private WeiboDbHelper mDbHelper;
    private ContentResolver mResolver;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO,WEIBO);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO_PICS,WEIBO_PICS);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO_COMMENT,WEIBO_COMMENT);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_USER,USER);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_ACCOUNT,ACCOUNT);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_WEIBO + "/user",WEIBO_WITH_USER);
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
        String tableName;
        switch (code){
            case WEIBO:
                cursor = db.query(
                        WeiboEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case WEIBO_PICS:
                cursor = db.query(
                        WeiboPicsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case WEIBO_COMMENT:
                cursor = db.query(
                        WeiboCommentEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case USER:
                cursor = db.query(
                            UserEntry.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);
                break;
            case ACCOUNT:
                cursor = db.query(
                        AccountEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case WEIBO_WITH_USER:
                tableName = WeiboEntry.TABLE_NAME + " INNER JOIN " +
                        UserEntry.TABLE_NAME +" ON " + WeiboEntry.TABLE_NAME +
                        "." + WeiboEntry.COLUMN_USER_ID + " = " + UserEntry.TABLE_NAME +
                        "." + UserEntry.COLUMN_USER_ID;
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
                type = WeiboEntry.CONTENT_TYPE;
                break;
            case WEIBO_PICS:
                type = WeiboPicsEntry.CONTENT_TYPE;
                break;
            case WEIBO_COMMENT:
                type = WeiboCommentEntry.CONTENT_TYPE;
                break;
            case USER:
                type = UserEntry.CONTENT_TYPE;
                break;
            case ACCOUNT:
                type = AccountEntry.CONTENT_TYPE;
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
                rowId = db.replace(WeiboEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId,uri, WeiboEntry.buildWeiboUri(rowId));
                break;
            case WEIBO_PICS:
                rowId = db.replace(WeiboPicsEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId, uri, WeiboPicsEntry.buildWeiboPicsUri(rowId));
                break;
            case WEIBO_COMMENT:
                rowId = db.replace(WeiboCommentEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId, uri, WeiboCommentEntry.buildWeiboCommentUri(rowId));
                break;
            case USER:
                rowId = db.replace(UserEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId,uri, UserEntry.buildUserUri(rowId));
                break;
            case ACCOUNT:
                rowId = db.replace(AccountEntry.TABLE_NAME,null,values);
                retUri = insertSuccessfulOrThrow(rowId, uri, AccountEntry.buildAccountUri(rowId));
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
                    rowId = db.replace(WeiboEntry.TABLE_NAME, null, contentValues);
                    insertSuccessfulOrThrow(rowId,uri,null);
                    ++count;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            case WEIBO_PICS:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.insert(WeiboPicsEntry.TABLE_NAME,null,contentValues);
                    insertSuccessfulOrThrow(rowId,uri,null);
                    ++count;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            case WEIBO_COMMENT:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.insert(WeiboCommentEntry.TABLE_NAME,null,contentValues);
                    insertSuccessfulOrThrow(rowId,uri,null);
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
                rowId = db.delete(WeiboEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case WEIBO_PICS:
                rowId = db.delete(WeiboPicsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case WEIBO_COMMENT:
                rowId = db.delete(WeiboCommentEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER:
                rowId = db.delete(UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ACCOUNT:
                rowId = db.delete(AccountEntry.TABLE_NAME, selection, selectionArgs);
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
                rowId = db.update(WeiboEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId, uri,null);
                break;
            case WEIBO_PICS:
                rowId = db.update(WeiboPicsEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId, uri,null);
                break;
            case WEIBO_COMMENT:
                rowId = db.update(WeiboCommentEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId,uri,null);
                break;
            case USER:
                rowId = db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId,uri,null);
                break;
            case ACCOUNT:
                rowId = db.update(AccountEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId,uri,null);
                break;
            default:
                throw new android.database.SQLException("No such a uri " + uri);
        }
        mResolver.notifyChange(uri,null);

        return rowId;
    }

    private Uri insertSuccessfulOrThrow(long rowId,Uri throwUri,Uri retUri){
        if(rowId > 0){
            return retUri;
        }
        throw new android.database.SQLException("Failed to insert row into " + throwUri);
    }
}
