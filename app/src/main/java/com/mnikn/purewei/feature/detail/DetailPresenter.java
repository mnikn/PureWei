package com.mnikn.purewei.feature.detail;

import android.content.Context;

import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.base.WeiboPresenter;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailPresenter extends WeiboPresenter implements IDetailPresenter{

    private long mWeiboId;

    public DetailPresenter(Context context,IDetailView view,long weiboId) {
        super(context,view);
        mWeiboId = weiboId;
    }

    @Override
    public void doRefresh(int page) {
        RequestManager.getComment(
                getContext(),
                getView(),
                Constant.REFRESH,
                page,
                mWeiboId);
    }

    @Override
    public void doLoadMore(int page) {
        RequestManager.getComment(
                getContext(),
                getView(),
                Constant.LOAD_MORE,
                page,
                mWeiboId);
    }
}
