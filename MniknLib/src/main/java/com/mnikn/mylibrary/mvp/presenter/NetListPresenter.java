package com.mnikn.mylibrary.mvp.presenter;

import android.content.Context;

import com.mnikn.mylibrary.mvp.view.INetListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetListPresenter<V extends INetListView> extends BasePresenter<V> implements INetListPresenter {

    private boolean mIsLoading = false;
    private int mPage;

    public NetListPresenter(Context context,V view) {
        super(context,view);
    }

    @Override
    public void refresh() {

        getView().onRefresh();
        mIsLoading = true;
        mPage = 1;
        refreshRequest(mPage);
        ++mPage;
    }

    @Override
    public void loadMore() {
        getView().onLoadMore();
        mIsLoading = true;
        if(mPage < 2){
            mPage = 2;
        }
        loadMoreRequest(mPage);
        ++mPage;
    }

    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public abstract void refreshRequest(int page);
    public abstract void loadMoreRequest(int page);
}
