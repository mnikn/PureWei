package com.mnikn.purewei.support.base;

import android.content.Context;

import com.mnikn.mylibrary.mvp.presenter.NetListPresenter;
import com.mnikn.mylibrary.mvp.view.INetListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class WeiboPresenter<V extends INetListView> extends NetListPresenter<V> {

    private int mPage;

    public WeiboPresenter(Context context,V view) {
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
