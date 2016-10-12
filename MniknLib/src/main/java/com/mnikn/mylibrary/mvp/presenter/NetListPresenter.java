package com.mnikn.mylibrary.mvp.presenter;

import android.content.Context;

import com.mnikn.mylibrary.mvp.view.INetListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetListPresenter extends BasePresenter implements INetListPresenter {

    private boolean mIsLoading = false;

    public NetListPresenter(Context context, INetListView view) {
        super(context,view);
    }

    @Override
    public INetListView getView() {
        return (INetListView) super.getView();
    }

    @Override
    public void refresh() {
        getView().onRefresh();
        mIsLoading = true;
        refreshRequest();
    }

    @Override
    public void loadMore() {
        getView().onLoadMore();
        mIsLoading = true;
        loadMoreRequest();
    }

    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public abstract void refreshRequest();
    public abstract void loadMoreRequest();
}
