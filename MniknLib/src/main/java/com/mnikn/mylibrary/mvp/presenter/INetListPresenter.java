package com.mnikn.mylibrary.mvp.presenter;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface INetListPresenter extends IPresenter {
    void refresh();
    void loadMore();
    boolean isLoading();
    void setIsLoading(boolean isLoading);
    void cancelLoading();
}
