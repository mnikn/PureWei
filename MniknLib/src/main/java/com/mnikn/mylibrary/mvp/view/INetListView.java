package com.mnikn.mylibrary.mvp.view;

import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface INetListView extends IListView{
    void onRefresh();
    void onRefreshFinish();
    void onLoadMore();
    void onLoadMoreFinish();
}
