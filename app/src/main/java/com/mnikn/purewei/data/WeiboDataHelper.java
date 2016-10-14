package com.mnikn.purewei.data;

import android.content.ContentResolver;
import android.database.Cursor;

import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.App;
import com.mnikn.purewei.model.AccountModel;
import com.mnikn.purewei.model.CommentModel;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.model.WeiboModel;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboDataHelper {

    private static WeiboDataHelper INSTANCE;
    private ContentResolver mResolver;

    private WeiboDataHelper() {
        mResolver = App.getAppContext().getContentResolver();
    }

    public static WeiboDataHelper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WeiboDataHelper();
        }
        return INSTANCE;
    }


    public Cursor getWeiboWithUser(){
        return mResolver.query(
                WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                null,
                null,
                null,
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }
    public Cursor getWeiboWithUserByWeiboId(long weiboId){
        return mResolver.query(
                WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                null,
                WeiboContract.WeiboEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{NumberUtil.longToString(weiboId)},
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }
    public WeiboModel getWeiboModel(){
        return new WeiboModel(getWeiboWithUser());
    }

    public Cursor getWeiboPics(long weiboId){
        return mResolver.query(
                WeiboContract.WeiboPicsEntry.CONTENT_URI,
                null,
                WeiboContract.WeiboPicsEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{NumberUtil.longToString(weiboId)},
                null);
    }

    public Cursor getUser(long uid){
        return mResolver.query(
                WeiboContract.UserEntry.CONTENT_URI,
                null,
                WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{NumberUtil.longToString(uid)},
                null);
    }
    public UserModel getUserModel(long uid){
        Cursor cursor = getUser(uid);
        cursor.moveToFirst();
        return new UserModel(cursor);
    }

    public Cursor getCommentWithUser(long uid){
        return mResolver.query(
                WeiboContract.WeiboCommentEntry.buildWeiboCommentWithUserUri(),
                null,
                WeiboContract.UserEntry.TABLE_NAME + "." + WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{NumberUtil.longToString(uid)},
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }
    public CommentModel getCommentModel(long uid){
        return new CommentModel(getCommentWithUser(uid));
    }

    public Cursor getAccount(){
        return mResolver.query(
                WeiboContract.AccountEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }
    public AccountModel getAccountModel(){
        return new AccountModel(getAccount());
    }
}
