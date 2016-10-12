package com.mnikn.purewei.feature.home;

import com.mnikn.mylibrary.mvp.view.INetListView;
import com.mnikn.purewei.model.UserModel;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends INetListView {
    void setUserView(UserModel account);
}
