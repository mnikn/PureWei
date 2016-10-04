package com.mnikn.mylibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class GlideUtil {

    public static void setImage(final Context context,String url,final ImageView imageView){
        setImage(context,url,imageView,null);
    }

    public static void setImage(final Context context,String url, final ImageView imageView,Drawable placeHolder){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .approximate()
                .placeholder(placeHolder)
                .into(imageView);
    }

    public static void setCircleImage(final Context context,String url, final CircleImageView imageView){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
