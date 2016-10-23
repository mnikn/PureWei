package com.mnikn.library.presenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * All presenter need to extends this
 * Presenter lifecycle: onCreate() -> onTakeView() -> onDropView() -> onDestory()
 */
public class Presenter<View> {

    private View mView;

    public Presenter() {}

    protected void onCreate() {}
    protected void onTakeView() {}
    protected void onDropView() {}
    protected void onDestroy() {}

    public View getView(){
        return mView;
    }

    /**
     * Create presenter
     */
    public void create(){
        onCreate();
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
