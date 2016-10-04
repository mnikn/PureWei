package com.mnikn.mylibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DrawableUtil {

    public static Drawable getDrawable(Context context,int drawableId){
        return context.getResources().getDrawable(drawableId);
    }
}
