package com.mnikn.library.utils;

import android.view.View;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ViewUtils {

    public static void setGone(View view,boolean isGone){
        if(view == null) return;
        if(isGone){
            view.setVisibility(View.GONE);
        }
        else{
            view.setVisibility(View.VISIBLE);
        }
    }
    public static void setInVisible(View view,boolean isInvisible){
        if(view == null) return;
        if(isInvisible){
            view.setVisibility(View.INVISIBLE);
        }
        else{
            view.setVisibility(View.VISIBLE);
        }
    }
}
