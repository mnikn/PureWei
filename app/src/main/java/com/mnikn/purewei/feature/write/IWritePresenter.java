package com.mnikn.purewei.feature.write;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IWritePresenter extends IPresenter {
    void postWeibo(String content);
}
