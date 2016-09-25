package com.mnikn.purewei.feature.user;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter implements IUserPresenter {

    private IUserView mView;

    public UserPresenter(IUserView view) {
        mView = view;
    }
}
