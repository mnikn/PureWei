package com.mnikn.purewei.mvp;

import com.mnikn.mylibrary.interfaces.IView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends IView {
    void setUserView(String url,String name);
    void onRefresh();
    void onRefreshFinish();
    void onLoadMoreFinish();
}
