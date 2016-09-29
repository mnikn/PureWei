package com.mnikn.mylibrary.mvp;

import android.content.Context;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseListPresenter extends BasePresenter implements IListPresenter {

    private boolean mIsLoading = false;

    public BaseListPresenter(Context context,IListView view) {
        super(context,view);
    }

    @Override
    public IListView getView() {
        return (IListView) super.getView();
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
