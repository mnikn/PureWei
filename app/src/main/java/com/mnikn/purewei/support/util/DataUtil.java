package com.mnikn.purewei.support.util;

import android.content.Context;
import android.database.Cursor;

import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DataUtil {
    public static boolean hasUserId(Context context,long userId){
        Cursor cursor = context.getContentResolver().query(
                WeiboContract.UserEntry.CONTENT_URI,
                null,
                WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{NumberUtil.longToString(userId)},
                null);
        return !com.mnikn.mylibrary.util.DataUtil.isEmpty(cursor);
    }


}
