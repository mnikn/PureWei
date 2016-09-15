package com.mnikn.purewei.mvp.presenter;

import android.content.Intent;

import com.mnikn.mylibrary.interfaces.IPresenter;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public interface IHomePresenter extends IPresenter {
    void authorize();
    void getAccountInfo();
    void refresh();
    void loadMore();
    void authorizeCallBack(int requestCode, int resultCode, Intent data);
}
