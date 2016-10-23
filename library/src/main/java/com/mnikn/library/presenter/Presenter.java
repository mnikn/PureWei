package com.mnikn.library.presenter;

import android.os.Bundle;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * All presenter need to extends this
 * Presenter lifecycle: onCreate() -> onTakeView() -> onDropView() -> onDestory()
 */
public class Presenter<View> {

    private View mView;
    private Bundle mBundle;

    public Presenter() {}
    public Presenter(Bundle bundle) {
        mBundle = bundle;
    }

    protected void onCreate(Bundle bundle) {}
    protected void onTakeView() {}
    protected void onDropView() {}
    protected void onDestroy() {}

    public View getView(){
        return mView;
    }
    public Bundle getBundle(){
        return mBundle;
    }

    /**
     * Create presenter
     */
    public void create(){
        onCreate(mBundle);
    }
    public void destroy(){
        onDestroy();
    }
    public void takeView(View view){
        mView = view;
        onTakeView();
    }
    public void dropView(){
        onDropView();
        mView = null;
    }
}
