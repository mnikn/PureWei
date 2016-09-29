package com.mnikn.mylibrary.mvp;

import android.content.Context;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BasePresenter implements IPresenter {

    private Context mContext;
    private IView mView;

    public BasePresenter(Context context,IView view){
        mContext = context;
        mView = view;
    }

    public Context getContext(){
        return mContext;
    }

    public IView getView(){
        return mView;
    }

}
