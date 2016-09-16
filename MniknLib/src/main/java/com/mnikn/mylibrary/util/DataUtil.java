package com.mnikn.mylibrary.util;

import android.database.Cursor;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DataUtil {
    public static boolean isEmpty(Cursor cursor){
        return (cursor == null || cursor.getCount() == 0);
    }

    public static <T> T jsonToBean(String json,Class<T> tClass){
        return new Gson().fromJson(json,tClass);
    }
}
