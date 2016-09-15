package com.mnikn.mylibrary.util;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class NumberUtil {

    public static boolean notZero(int num){
        return num != 0;
    }

    public static String longToString(long num){
        return String.valueOf(num);
    }
    public static long stringToLong(String num){
        return Long.parseLong(num);
    }
}
