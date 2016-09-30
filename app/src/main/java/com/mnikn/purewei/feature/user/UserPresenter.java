package com.mnikn.purewei.feature.user;

import android.content.Context;

import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.base.WeiboPresenter;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter extends WeiboPresenter implements IUserPresenter {

    public UserPresenter(Context context,IUserView view) {
        super(context,view);
    }

    @Override
    public void doRefresh(int page) {
        RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.REFRESH,
                page);
    }

    @Override
    public void doLoadMore(int page) {
        RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.LOAD_MORE,
                page);
    }
}
