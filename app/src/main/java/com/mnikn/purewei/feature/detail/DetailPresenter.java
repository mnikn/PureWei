package com.mnikn.purewei.feature.detail;

import android.content.Context;

import com.mnikn.mylibrary.mvp.presenter.INetListPresenter;
import com.mnikn.mylibrary.mvp.presenter.NetListPresenter;
import com.mnikn.mylibrary.mvp.view.INetListView;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailPresenter extends NetListPresenter<INetListView> implements INetListPresenter {

    private long mWeiboId;
    private Observable commentObservable;

    public DetailPresenter(Context context,INetListView view,long weiboId) {
        super(context,view);
        mWeiboId = weiboId;
    }

    @Override
    public void refreshRequest(int page) {
        commentObservable = RequestManager.getComment(
                getContext(),
                getView(),
                Constant.REFRESH,
                page,
                mWeiboId);
    }

    @Override
    public void loadMoreRequest(int page) {
        commentObservable = RequestManager.getComment(
                getContext(),
                getView(),
                Constant.LOAD_MORE,
                page,
                mWeiboId);
    }

    @Override
    public void cancelLoading() {
        setIsLoading(false);
        RequestManager.cancelRequest(commentObservable);
    }


}
