package com.mnikn.purewei.support.util;

import com.mnikn.library.utils.Strings;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class TextUtil {
    public static String cutHerfInfo(String source){
        if(Strings.isBlank(source)){
            return source;
        }
        int start = source.indexOf(">");
        int end = source.lastIndexOf("<");
        return source.substring(start + 1,end);
    }
}
