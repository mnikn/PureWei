package com.mnikn.purewei.support.base;

import android.content.Context;

import com.mnikn.mylibrary.mvp.BaseListPresenter;
import com.mnikn.mylibrary.mvp.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class WeiboPresenter extends BaseListPresenter {

    private int mPage;

    public WeiboPresenter(Context context, IListView view) {
        super(context, view);
    }

    @Override
    public void refreshRequest() {
        mPage = 1;
        doRefresh(mPage);
        ++mPage;
    }

    @Override
    public void loadMoreRequest() {
        if(mPage < 2){
            mPage = 2;
        }
        doLoadMore(mPage);
        ++mPage;
    }

    public abstract void doRefresh(int page);
    public abstract void doLoadMore(int page);
}
