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

    private static final int STATUS = 100;
    private static final int PICTURE = 101;
    private static final int COMMENT = 102;
    private static final int USER = 103;
    private static final int ACCOUNT = 104;
    private static final int DRAFT = 105;
    private static final int STATUS_WITH_USER = 106;
    private static final int COMMENT_WITH_USER = 107;
    private static UriMatcher sUriMatcher;

    private WeiboDbHelper mDbHelper;
    private ContentResolver mResolver;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_STATUS, STATUS);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_PICTURE, PICTURE);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_COMMENT, COMMENT);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_USER,USER);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_ACCOUNT,ACCOUNT);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_DRAFT,DRAFT);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_STATUS + "/user", STATUS_WITH_USER);
        sUriMatcher.addURI(WeiboContract.CONTENT_AUTHORITY,WeiboContract.PATH_COMMENT + "/user",COMMENT_WITH_USER);
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
            case STATUS:
                cursor = db.query(
                        StatusEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case PICTURE:
                cursor = db.query(
                        PictureEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case COMMENT:
                cursor = db.query(
                        CommentEntry.TABLE_NAME,
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
            case DRAFT:
                cursor = db.query(
                        DraftEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case STATUS_WITH_USER:
                tableName = StatusEntry.TABLE_NAME + " INNER JOIN " +
                        UserEntry.TABLE_NAME +" ON " + StatusEntry.TABLE_NAME +
                        "." + StatusEntry.COLUMN_USER_ID + " = " + UserEntry.TABLE_NAME +
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
            case COMMENT_WITH_USER:
                tableName = CommentEntry.TABLE_NAME + " INNER JOIN " +
                        UserEntry.TABLE_NAME +" ON " + CommentEntry.TABLE_NAME +
                        "." + CommentEntry.COLUMN_COMMENT_USER_ID + " = " + UserEntry.TABLE_NAME +
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
            case STATUS:
                type = StatusEntry.CONTENT_TYPE;
                break;
            case PICTURE:
                type = PictureEntry.CONTENT_TYPE;
                break;
            case COMMENT:
                type = CommentEntry.CONTENT_TYPE;
                break;
            case USER:
                type = UserEntry.CONTENT_TYPE;
                break;
            case ACCOUNT:
                type = AccountEntry.CONTENT_TYPE;
                break;
            case DRAFT:
                type = DraftEntry.CONTENT_TYPE;
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
            case STATUS:
                rowId = db.replace(StatusEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId,uri, StatusEntry.buildStatusUri(rowId));
                break;
            case PICTURE:
                rowId = db.replace(PictureEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId, uri, PictureEntry.buildPictureUri(rowId));
                break;
            case COMMENT:
                rowId = db.replace(CommentEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId, uri, CommentEntry.buildCommentUri(rowId));
                break;
            case USER:
                rowId = db.replace(UserEntry.TABLE_NAME, null, values);
                retUri = insertSuccessfulOrThrow(rowId,uri, UserEntry.buildUserUri(rowId));
                break;
            case ACCOUNT:
                rowId = db.replace(AccountEntry.TABLE_NAME,null,values);
                retUri = insertSuccessfulOrThrow(rowId, uri, AccountEntry.buildAccountUri(rowId));
                break;
            case DRAFT:
                rowId = db.replace(DraftEntry.TABLE_NAME,null,values);
                retUri = insertSuccessfulOrThrow(rowId, uri, DraftEntry.buildDraftUri(rowId));
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
            case STATUS:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.replace(StatusEntry.TABLE_NAME, null, contentValues);
                    insertSuccessfulOrThrow(rowId,uri,null);
                    ++count;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            case PICTURE:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.insert(PictureEntry.TABLE_NAME,null,contentValues);
                    insertSuccessfulOrThrow(rowId,uri,null);
                    ++count;
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                break;
            case COMMENT:
                db.beginTransaction();
                for(ContentValues contentValues : values){
                    rowId = db.insert(CommentEntry.TABLE_NAME,null,contentValues);
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
            case STATUS:
                rowId = db.delete(StatusEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PICTURE:
                rowId = db.delete(PictureEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case COMMENT:
                rowId = db.delete(CommentEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER:
                rowId = db.delete(UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ACCOUNT:
                rowId = db.delete(AccountEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DRAFT:
                rowId = db.delete(DraftEntry.TABLE_NAME, selection, selectionArgs);
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
            case STATUS:
                rowId = db.update(StatusEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId, uri,null);
                break;
            case PICTURE:
                rowId = db.update(PictureEntry.TABLE_NAME, values, selection, selectionArgs);
                insertSuccessfulOrThrow(rowId, uri,null);
                break;
            case COMMENT:
                rowId = db.update(CommentEntry.TABLE_NAME, values, selection, selectionArgs);
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
            case DRAFT:
                rowId = db.update(DraftEntry.TABLE_NAME, values, selection, selectionArgs);
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
