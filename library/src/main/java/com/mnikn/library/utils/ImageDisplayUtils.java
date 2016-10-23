package com.mnikn.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ImageDisplayUtils {


    public static void displayFromFile(Context context,File file,ImageView view){
        ImageLoader.getInstance().displayFromFile(context, file, view);
    }
    public static void displayFromNet(Context context,String url,ImageView view){
        ImageLoader.getInstance().displayFromNet(context, url, view);
    }
    public static void displayFromNet(final Context context,String url,CircleImageView view){
        ImageLoader.getInstance().displayFromNet(context,url,new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        }.getView());
    }
}
