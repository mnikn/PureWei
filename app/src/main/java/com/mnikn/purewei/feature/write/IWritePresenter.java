package com.mnikn.purewei.feature.write;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IWritePresenter extends IPresenter {
    void createWeibo(String content);
    void createComment(String content,long id);
}
