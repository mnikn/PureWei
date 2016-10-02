package com.mnikn.purewei.feature.home;

import android.content.Intent;

import com.mnikn.mylibrary.mvp.IListPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomePresenter extends IListPresenter {
    void authorize();
    void setWeiboType(int type);
    void authorizeCallBack(int requestCode, int resultCode, Intent data);
}
