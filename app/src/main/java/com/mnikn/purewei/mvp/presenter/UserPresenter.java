package com.mnikn.purewei.mvp.presenter;

import com.mnikn.purewei.mvp.IUserView;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class UserPresenter implements IUserPresenter {

    private IUserView mView;

    public UserPresenter(IUserView view) {
        mView = view;
    }
}
