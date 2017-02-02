package com.mnikn.library.utils;

import android.database.Cursor;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DataUtils {
    public static boolean isEmpty(Cursor cursor){
        return (cursor == null || cursor.getCount() == 0);
    }
    public static boolean isEmpty(List list){
        return (list == null || list.isEmpty());
    }
}
