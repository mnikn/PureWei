package com.mnikn.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ToastUtils {

    public static void makeToastShort(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
    public static void makeToastLong(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
}
