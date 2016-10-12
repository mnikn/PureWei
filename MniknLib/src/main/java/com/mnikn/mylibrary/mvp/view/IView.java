package com.mnikn.mylibrary.mvp.view;

import android.view.View;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IView {
    void setupViews(View parent);
    <P extends IPresenter> P getPresenter();
}
