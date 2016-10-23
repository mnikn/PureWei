package com.mnikn.library.presenter;

import com.mnikn.library.view.INetView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetPresenter<View extends INetView> extends Presenter<View>{

    protected boolean mIsLoading;
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
    public void refreshFinish(){
        mIsLoading = false;
        getView().onRefreshFinish();
        mPage = 2;
    }
    public void loadMoreFinish(){
        mIsLoading = false;
        getView().onLoadMoreFinish();
        ++mPage;
    }
    public void setLoading(boolean loading){
        mIsLoading = loading;
    }
    public boolean isLoading(){
        return mIsLoading;
    }
    public void setPageSize(int pageSize){
        mPageSize = pageSize;
    }

}
