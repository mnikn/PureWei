package com.mnikn.purewei.feature.home;

import com.mnikn.library.view.net.INetView;
import com.mnikn.purewei.model.User;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomeView extends INetView {
    void setUserView(User account);
}
