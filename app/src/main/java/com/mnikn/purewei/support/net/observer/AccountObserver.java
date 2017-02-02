package com.mnikn.purewei.support.net.observer;

import android.util.Log;

import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.model.User;
import com.mnikn.purewei.support.Constants;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountObserver implements Observer<User>{

    private IHomeView mView;

    public AccountObserver(IHomeView view){
        mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(User value) {
        Log.e("sad","in");
        value.type = Constants.USER_ACCOUNT;
        UserDao.insertUser(value);
        mView.setUserView(value);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
