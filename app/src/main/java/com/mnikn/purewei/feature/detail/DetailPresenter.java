package com.mnikn.purewei.feature.detail;

import android.content.Context;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailPresenter extends NetPresenter<DetailFragment> {

    private long mWeiboId;
    private Observable commentObservable;

    public DetailPresenter(Context context,long weiboId) {
        super(context);
        mWeiboId = weiboId;
    }

    public void cancelLoading() {
        mIsLoading = false;
        RequestManager.cancelRequest(commentObservable);
    }


    @Override
    protected void request(int page) {
        if(page == 1){
            commentObservable = RequestManager.getComment(
                    this,
                    Constant.REFRESH,
                    mWeiboId);
        }
        else{
            commentObservable = RequestManager.getComment(
                    this,
                    Constant.LOAD_MORE,
                    mWeiboId);
        }
    }

    public long getWeiboId(){
        return mWeiboId;
    }
}
