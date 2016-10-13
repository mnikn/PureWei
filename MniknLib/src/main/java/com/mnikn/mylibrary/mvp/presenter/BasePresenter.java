package com.mnikn.mylibrary.mvp.presenter;

import android.content.Context;

import com.mnikn.mylibrary.mvp.view.IView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BasePresenter<V extends IView> implements IPresenter {

    private Context mContext;
    private V mView;

    public  BasePresenter(Context context,V view){
        mContext = context;
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    public Context getContext(){
        return mContext;
    }


}
