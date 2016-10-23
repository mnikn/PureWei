package com.mnikn.purewei.feature.home;

import com.mnikn.library.view.INetView;
import com.mnikn.purewei.model.UserModel;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends INetView {
    void setUserView(UserModel account);
}
