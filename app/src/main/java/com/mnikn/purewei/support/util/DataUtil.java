package com.mnikn.purewei.support.util;

import android.content.Context;
import android.database.Cursor;

import com.mnikn.library.utils.DataUtils;
import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DataUtil {
    public static boolean hasUserId(Context context,long userId){
        Cursor cursor = context.getContentResolver().query(
                WeiboContract.UserEntry.CONTENT_URI,
                null,
                WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{Numbers.longToString(userId)},
                null);
        return !DataUtils.isEmpty(cursor);
    }

    public static boolean hasAccountId(Context context,long userId){
        Cursor cursor = context.getContentResolver().query(
                WeiboContract.AccountEntry.CONTENT_URI,
                null,
                WeiboContract.AccountEntry.COLUMN_ID + " = ?",
                new String[]{Numbers.longToString(userId)},
                null);
        return !DataUtils.isEmpty(cursor);
    }


}
