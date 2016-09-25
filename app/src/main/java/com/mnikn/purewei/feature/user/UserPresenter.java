package com.mnikn.purewei.feature.user;

import android.content.Context;

import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter implements IUserPresenter {

    private int mPage;
    private IUserView mView;
    private Context mContext;

    public UserPresenter(Context context,IUserView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void refresh() {
        mPage = 0;
        RequestManager.getHomeWeibo(
                mContext,
                mView,
                Constant.REFRESH,
                mPage
                );
        ++mPage;
    }

    @Override
    public void loadMore() {
        RequestManager.getHomeWeibo(
                mContext,
                mView,
                Constant.LOAD_MORE,
                mPage
        );
        ++mPage;
    }
}
