package com.mnikn.purewei.feature.draft;

import android.content.Context;

import com.mnikn.mylibrary.mvp.presenter.ListPresenter;
import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DraftPresenter extends ListPresenter {

    public DraftPresenter(Context context, IListView view) {
        super(context, view);
    }
}
