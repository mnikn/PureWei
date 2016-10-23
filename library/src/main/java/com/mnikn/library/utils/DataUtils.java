package com.mnikn.library.utils;

import android.database.Cursor;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DataUtils {
    public static int getLastIndex(List list){
        if(list == null || list.isEmpty()) return -1;
        return list.size() - 1;
    }

    public static boolean isEmpty(Cursor cursor){
        return (cursor == null || cursor.getCount() == 0);
    }

    public static <T> T jsonToBean(String json,Class<T> tClass){
        return new Gson().fromJson(json,tClass);
    }
}
