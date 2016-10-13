package com.mnikn.mylibrary.mvp.view.fragment;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.mvp.presenter.IListPresenter;
import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class RecyclerFragment <P extends IListPresenter> extends BaseFragment<P>
        implements IListView{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

}
