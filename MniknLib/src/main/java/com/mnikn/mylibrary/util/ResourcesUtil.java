package com.mnikn.mylibrary.util;

import android.content.Context;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ResourcesUtil {
    public static String[] getStringArray(Context context,int arrayId){
        return context.getResources().getStringArray(arrayId);
    }
}
