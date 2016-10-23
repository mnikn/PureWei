package com.mnikn.purewei.feature.user;

import android.content.Context;

import com.mnikn.library.presenter.NetPresenter;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter extends NetPresenter<UserFragment> {

    private Observable homeWeiboObservale;

    public UserPresenter(Context context) {
        super(context);
    }


    public void cancelLoading() {
        mIsLoading = false;
        RequestManager.cancelRequest(homeWeiboObservale);
    }

    @Override
    protected void request(int page) {
        if(page == 1){
            homeWeiboObservale = RequestManager.getUserWeibo(
                    getContext(),
                    getView(),
                    Constant.REFRESH,
                    page);
        }
        else{
            homeWeiboObservale = RequestManager.getUserWeibo(
                    getContext(),
                    getView(),
                    Constant.LOAD_MORE,
                    page);
        }
    }
}
