package com.mnikn.mylibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.mnikn.mylibrary.interfaces.ImageLoaderInterface;

import java.io.File;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class GlideImageLoader implements ImageLoaderInterface {

    private DrawableTypeRequest mDrawableTypeRequest;

    @Override
    public void setPlaceHolder(Drawable drawable) {
        mDrawableTypeRequest.placeholder(drawable);
    }
    @Override
    public void setPlaceHolder(int resourceId) {
        mDrawableTypeRequest.placeholder(resourceId);
    }

    @Override
    public void displayFromFile(Context context,File file,ImageView imageView) {
        mDrawableTypeRequest = load(context,file);
        mDrawableTypeRequest
                .asBitmap()
                .fitCenter()
                .approximate()
                .into(imageView);
    }

    @Override
    public void displayFromNet(Context context,String url,ImageView imageView) {
        mDrawableTypeRequest = load(context,url);
        mDrawableTypeRequest
                .asBitmap()
                .fitCenter()
                .approximate()
                .into(imageView);
    }

    private DrawableTypeRequest load(Context context,File file){
        return Glide.with(context).load(file);
    }
    private DrawableTypeRequest load(Context context,String url){
        return Glide.with(context).load(url);
    }
}
