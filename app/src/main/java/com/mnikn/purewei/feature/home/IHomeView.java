package com.mnikn.purewei.feature.home;

import com.mnikn.mylibrary.mvp.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends IListView {
    void setUserView(String url,String name);
}
