package com.mnikn.mylibrary.util;

import android.database.Cursor;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DataUtil {
    public static boolean isEmpty(Cursor cursor){
        return (cursor == null || cursor.getCount() == 0);
    }
}
