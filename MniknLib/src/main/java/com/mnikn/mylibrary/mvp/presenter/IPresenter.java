package com.mnikn.mylibrary.mvp.presenter;

import com.mnikn.mylibrary.mvp.view.IView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IPresenter {
    <V extends IView> V getView();
}
