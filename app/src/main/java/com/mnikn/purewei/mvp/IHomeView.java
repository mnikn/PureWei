package com.mnikn.purewei.mvp;

import com.mnikn.mylibrary.interfaces.IView;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public interface IHomeView extends IView {
    void setUserView(String url,String name);
    void onRefresh();
    void onRefreshFinish();
    void onLoadMoreFinish();
}
