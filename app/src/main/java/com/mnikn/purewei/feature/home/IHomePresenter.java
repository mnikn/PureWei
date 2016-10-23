package com.mnikn.purewei.feature.home;

import android.content.Intent;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IHomePresenter {
    void authorize();
    void setWeiboType(int type);
    void authorizeCallBack(int requestCode, int resultCode, Intent data);
}
