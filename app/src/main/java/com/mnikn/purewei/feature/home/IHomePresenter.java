package com.mnikn.purewei.feature.home;

import android.content.Intent;

import com.mnikn.mylibrary.mvp.IPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomePresenter extends IPresenter {
    void authorize();
    void getAccountInfo();
    void refresh();
    void loadMore();
    void authorizeCallBack(int requestCode, int resultCode, Intent data);
}
