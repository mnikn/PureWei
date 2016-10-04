package com.mnikn.purewei.support.net.observer;

import android.content.Context;

import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.support.net.RequestManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountUidObserver implements Observer<UserBean> {

    private Context mContext;
    private IHomeView mView;

    public AccountUidObserver(Context context,IHomeView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(UserBean value) {
        RequestManager.getAccountInfo(
                mContext,
                mView,
                value.id);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
