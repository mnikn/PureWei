package com.mnikn.purewei.mvp.presenter;

import com.mnikn.purewei.mvp.view.IUserView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter implements IUserPresenter {

    private IUserView mView;

    public UserPresenter(IUserView view) {
        mView = view;
    }
}
