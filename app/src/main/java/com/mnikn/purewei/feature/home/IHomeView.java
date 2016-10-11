package com.mnikn.purewei.feature.home;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.purewei.model.UserModel;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends IListView {
    void setUserView(UserModel account);
}
