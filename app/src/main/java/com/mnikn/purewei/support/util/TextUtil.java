package com.mnikn.purewei.support.util;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class TextUtil {
    public static String cutHerfInfo(String source){
        if(com.mnikn.mylibrary.util.TextUtil.isEmpty(source)){
            return source;
        }
        int start = source.indexOf(">");
        int end = source.lastIndexOf("<");
        return source.substring(start + 1,end);
    }
}
