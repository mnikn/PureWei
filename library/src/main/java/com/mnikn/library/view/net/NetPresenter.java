package com.mnikn.library.view.net;

import android.content.Context;

import com.mnikn.library.view.base.Presenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetPresenter<View extends INetView> extends Presenter<View> {

    private Context mContext;
    protected boolean mIsLoading;
    private int mPage = 1;
    private int mPageSize = Integer.MAX_VALUE;

    public NetPresenter(Context context){
        mContext = context;
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

    public Context getContext(){
        return mContext;
    }
    public int getPage(){
        return mPage;
    }

    public void setLoading(boolean loading){
        mIsLoading = loading;
    }
    public boolean isLoading(){
        return mIsLoading;
    }
    public void setPage(int page){
        mPage = page;
    }
    public void setPageSize(int pageSize){
        mPageSize = pageSize;
    }


}
