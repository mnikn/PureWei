package com.mnikn.library.utils;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Numbers {
    public static String intToString(int num) {
        return Integer.toString(num);
    }
    public static String longToString(long num){
        return String.valueOf(num);
    }
    public static long stringToLong(String num){
        return Long.parseLong(num);
    }
}
