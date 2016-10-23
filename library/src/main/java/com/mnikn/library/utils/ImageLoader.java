package com.mnikn.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.File;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * A Image diplay loader
 */
public class ImageLoader {

    private static ImageLoader SINGLETON;

    private ImageLoaderInterface mLoaderImpl;


    private ImageLoader() {
        //default imageLoader
        mLoaderImpl = new GlideImageLoader();
    }

    public static ImageLoader getInstance(){
        if(SINGLETON == null){
            SINGLETON = new ImageLoader();
        }
        return SINGLETON;
    }

    public void setImageLoader(ImageLoaderInterface loader){
        mLoaderImpl = loader;
    }
    public void setPlaceHolder(Drawable drawable) {
        mLoaderImpl.setPlaceHolder(drawable);
    }
    public void setPlaceHolder(int resourceId) {
        mLoaderImpl.setPlaceHolder(resourceId);
    }
    public void displayFromFile(Context context,File file,ImageView imageView) {
        mLoaderImpl.displayFromFile(context,file,imageView);
    }
    public void displayFromNet(Context context,String url,ImageView imageView) {
        mLoaderImpl.displayFromNet(context, url, imageView);
    }

    public interface ImageLoaderInterface {
        void setPlaceHolder(Drawable drawable);
        void setPlaceHolder(int resourceId);
        void displayFromFile(Context context,File file,ImageView imageView);
        void displayFromNet(Context context,String url,ImageView imageView);
    }

}
