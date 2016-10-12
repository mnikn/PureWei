package com.mnikn.mylibrary.mvp.presenter;

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
