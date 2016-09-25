package com.mnikn.mylibrary.mvp;

import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IListView extends IView{
    void onRefresh();
    void onRefreshFinish();
    void onLoadMore();
    void onLoadMoreFinish();

    IListPresenter getPresenter();
    RecyclerView.Adapter getAdapter();
}
