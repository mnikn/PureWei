package com.mnikn.mylibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ToastUtil {

    public static void makeToastShort(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
    public static void makeToastLong(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
}
