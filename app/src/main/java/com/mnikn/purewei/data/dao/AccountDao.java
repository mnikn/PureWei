package com.mnikn.purewei.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountDao {

    public static List<Account> getAccounts() {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.AccountEntry.buildAccountUri(1),
                null,
                null,
                null,
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
        List<Account> accounts = new ArrayList<>();
        while (cursor.moveToNext()) {
            accounts.add(getAccount(cursor));
        }
        return accounts;
    }

    public static Account getAccount(long id) {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.StatusEntry.buildStatusUriWithUser(),
                null,
                WeiboContract.AccountEntry.COLUMN_ID + " = ?",
                new String[]{Numbers.longToString(id)},
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
        cursor.moveToFirst();

        return getAccount(cursor);
    }

    public static Account getAccount(Cursor cursor) {
        Account account = new Account();
        account.id = WeiboContract.AccountEntry.getId(cursor);
        account.expireTime = WeiboContract.AccountEntry.getExpiresTime(cursor);
        account.expireIn = WeiboContract.AccountEntry.getExpiresIn(cursor);
        account.accessToken = WeiboContract.AccountEntry.getAccessToken(cursor);
        account.refreshToken = WeiboContract.AccountEntry.getRefreshToken(cursor);
        return account;
    }

    public static void insertAccount(ContentValues values) {
        App.getAppContext().getContentResolver()
                .insert(WeiboContract.AccountEntry.CONTENT_URI, values);
    }

    public static void insertAccount(Account account) {
        ContentValues values = new ContentValues();

        values.put(WeiboContract.AccountEntry.COLUMN_ID,account.id);
        values.put(WeiboContract.AccountEntry.COLUMN_EXPIRES_TIME,account.expireTime);
        values.put(WeiboContract.AccountEntry.COLUMN_EXPIRES_IN,account.expireIn);
        values.put(WeiboContract.AccountEntry.COLUMN_ACCESS_TOKEN,account.accessToken);
        values.put(WeiboContract.AccountEntry.COLUMN_REFRESH_TOKEN,account.refreshToken);
        insertAccount(values);
    }

    public static void delete(long id){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.AccountEntry.CONTENT_URI,
                        WeiboContract.AccountEntry.COLUMN_ID + " = ?",
                        new String[]{Numbers.longToString(id)});
    }

    public static void clear(){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.AccountEntry.CONTENT_URI,
                        null,
                        null);
    }
}
