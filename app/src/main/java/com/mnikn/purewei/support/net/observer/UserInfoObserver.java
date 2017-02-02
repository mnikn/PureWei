package com.mnikn.purewei.support.net.observer;

import android.content.Context;

import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.model.User;
import com.mnikn.purewei.support.Constants;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserInfoObserver implements Observer<User> {

    private Context mContext;

    public UserInfoObserver(Context context){
        mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(User value) {
        value.type = Constants.USER_NORMAL;
        UserDao.insertUser(value);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
