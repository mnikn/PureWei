package com.mnikn.mylibrary.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.File;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface ImageLoaderInterface {
    void setPlaceHolder(Drawable drawable);
    void setPlaceHolder(int resourceId);
    void displayFromFile(Context context,File file,ImageView imageView);
    void displayFromNet(Context context,String url,ImageView imageView);
}
