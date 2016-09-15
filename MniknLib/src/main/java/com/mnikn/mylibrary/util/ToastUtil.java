package com.mnikn.mylibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class ToastUtil {

    public static void makeToastShort(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
    public static void makeToastLong(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
}
