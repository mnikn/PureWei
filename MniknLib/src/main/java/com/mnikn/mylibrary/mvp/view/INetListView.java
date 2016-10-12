package com.mnikn.mylibrary.mvp.view;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface INetListView extends IListView{
    void onRefresh();
    void onRefreshFinish();
    void onLoadMore();
    void onLoadMoreFinish();
}
