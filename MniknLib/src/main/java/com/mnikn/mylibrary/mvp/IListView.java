package com.mnikn.mylibrary.mvp;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IListView extends IView{
    void onRefresh();
    void onRefreshFinish();
    void onLoadMore();
    void onLoadMoreFinish();

}
