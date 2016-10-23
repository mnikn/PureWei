package com.mnikn.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ResourcesUtils {
    public static String[] getStringArray(Context context,int arrayId){
        return context.getResources().getStringArray(arrayId);
    }
    public static Drawable getDrawable(Context context,int drawableId){
        return context.getResources().getDrawable(drawableId);
    }
}
