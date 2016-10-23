package com.mnikn.library.presenter;

import android.os.Bundle;

import com.mnikn.library.view.NetView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetPresenter extends Presenter<NetView>{

    private boolean mIsLoading;
    private int mPage = 1;
    private int mPageSize = Integer.MAX_VALUE;

    public NetPresenter(Bundle bundle) {
        super(bundle);
    }

    protected abstract void request(int page);
    public void refresh(){
        if(!mIsLoading){
            mIsLoading = true;
            mPage = 1;
            getView().onRefresh();
            request(mPage);
        }
    }
    public void loadMore(){
        if(!mIsLoading){
            mIsLoading = true;
            mPage = mPageSize > 1 ? mPageSize : 2;
            getView().onLoadMore();
            request(mPage);
        }
    }
    public void loadFinish(){
        mIsLoading = false;
        getView().onLoadFinish();
        mPage = mPage > mPageSize ? mPageSize : mPage + 1;
    }
    public boolean isLoading(){
        return mIsLoading;
    }
    public void setPageSize(int pageSize){
        mPageSize = pageSize;
    }

}
