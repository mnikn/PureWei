package com.mnikn.library.utils;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Numbers {

    public static boolean isZero(int num){
        return num == 0;
    }
    public static boolean isZero(long num){
        return num == 0;
    }
    public static boolean notZero(int num){
        return !(isZero(num));
    }
    public static boolean notZero(long num){
        return !(isZero(num));
    }

    public static String intToString(int num){
        return String.valueOf(num);
    }
    public static int stringToInt(String num){
        return Integer.parseInt(num);
    }
    public static String longToString(long num){
        return String.valueOf(num);
    }
    public static long stringToLong(String num){
        return Long.parseLong(num);
    }
}
