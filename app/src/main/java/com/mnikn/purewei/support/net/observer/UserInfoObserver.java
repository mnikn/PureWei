package com.mnikn.purewei.support.net.observer;

import android.content.Context;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.bean.UserBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserInfoObserver implements Observer<UserBean> {

    private Context mContext;

    public UserInfoObserver(Context context){
        mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(UserBean value) {
        UserEntity entity = new UserEntity(value);
        mContext.getContentResolver().insert(WeiboContract.UserEntry.CONTENT_URI,
                entity.toContentValues(Constant.USER_NORAML));
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
