package com.mnikn.purewei.feature.detail;

import android.content.Context;

import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailPresenter implements IDetailPresenter{


    private int mPage;
    private long mWeiboId;
    private boolean mIsLoading;

    private Context mContext;
    private IDetailView mView;

    public DetailPresenter(Context context,IDetailView view,long weiboId) {
        initVariables(context, view, weiboId);
    }

    @Override
    public void refresh() {
        mPage = 1;
        mView.onRefresh();
        mIsLoading = true;
        RequestManager.getComment(
                mContext,
                mView,
                Constant.REFRESH,
                mPage,
                mWeiboId);
        ++mPage;
    }

    @Override
    public void loadMore() {
        mView.onLoadMore();
        RequestManager.getComment(
                mContext,
                mView,
                Constant.LOAD_MORE,
                mPage,
                mWeiboId);
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

    private void initVariables(Context context,IDetailView view,long weiboId){
        mContext = context;
        mView = view;
        mWeiboId = weiboId;
        mIsLoading = false;
        mPage = 1;
    }
}
