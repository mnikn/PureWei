package com.mnikn.mylibrary.mvp.presenter;

import android.content.Context;

import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ListPresenter<V extends IListView> extends BasePresenter<V>{

    public ListPresenter(Context context, V view) {
        super(context, view);
    }
}
