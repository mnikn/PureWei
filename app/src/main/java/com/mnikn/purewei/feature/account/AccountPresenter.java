package com.mnikn.purewei.feature.account;

import android.content.Context;

import com.mnikn.mylibrary.mvp.presenter.ListPresenter;
import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountPresenter extends ListPresenter {

    public AccountPresenter(Context context, IListView view) {
        super(context, view);
    }
}
