package com.mnikn.library.presenter;

import com.mnikn.library.view.INetView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetPresenter extends Presenter<INetView>{

    private boolean mIsLoading;
    private int mPage = 1;
    private int mPageSize = Integer.MAX_VALUE;

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
