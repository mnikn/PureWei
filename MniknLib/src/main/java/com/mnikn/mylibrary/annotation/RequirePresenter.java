package com.mnikn.mylibrary.annotation;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;

import java.lang.annotation.Inherited;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
@Inherited
public @interface RequirePresenter {
    Class<? extends IPresenter> value();
}
