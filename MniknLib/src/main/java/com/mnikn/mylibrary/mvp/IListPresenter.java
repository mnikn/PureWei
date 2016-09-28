package com.mnikn.mylibrary.mvp;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IListPresenter extends IPresenter{
    void refresh();
    void loadMore();
    boolean isLoading();
    void setIsLoading(boolean isLoading);
}
