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

    private boolean mIsLoading;

    public UserPresenter(Context context,IUserView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void refresh() {
        mPage = 1;
        mView.onRefresh();
        mIsLoading = true;
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
        if(mPage < 2){
            mPage = 2;
        }
        mView.onLoadMore();
        mIsLoading = true;
        RequestManager.getHomeWeibo(
                mContext,
                mView,
                Constant.LOAD_MORE,
                mPage
        );
        ++mPage;
    }

    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }
}
