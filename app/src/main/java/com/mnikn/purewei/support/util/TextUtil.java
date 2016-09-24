package com.mnikn.purewei.support.util;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
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
