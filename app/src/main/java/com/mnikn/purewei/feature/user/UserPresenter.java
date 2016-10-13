package com.mnikn.purewei.feature.user;

import android.content.Context;

import com.mnikn.mylibrary.mvp.presenter.NetListPresenter;
import com.mnikn.mylibrary.mvp.view.INetListView;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter extends NetListPresenter<INetListView> {

    private Observable homeWeiboObservale;

    public UserPresenter(Context context,INetListView view) {
        super(context,view);
    }

    @Override
    public void refreshRequest(int page) {
        homeWeiboObservale = RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.REFRESH,
                page);
    }

    @Override
    public void loadMoreRequest(int page) {
        homeWeiboObservale = RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.LOAD_MORE,
                page);
    }

    @Override
    public void cancelLoading() {
        RequestManager.cancelRequest(homeWeiboObservale);
    }
}
