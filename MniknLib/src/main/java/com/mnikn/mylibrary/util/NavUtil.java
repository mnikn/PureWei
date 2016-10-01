package com.mnikn.mylibrary.util;

import android.content.Context;
import android.content.Intent;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class NavUtil {

    public static <T extends Context> void navToActivity(T fromActivity,Class<T> toActivityClass){
        fromActivity.startActivity(new Intent(fromActivity,toActivityClass));
    }

    public static <T extends Context> void navToActivity(T fromActivity,Class<T> toActivityClass,
                                         String key,
                                         long data){
        Intent intent = new Intent(fromActivity, toActivityClass);
        intent.putExtra(key,data);
        fromActivity.startActivity(intent);
    }
}
