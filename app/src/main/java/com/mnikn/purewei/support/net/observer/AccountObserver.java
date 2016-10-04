package com.mnikn.purewei.support.net.observer;

import android.content.Context;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.bean.UserBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountObserver implements Observer<UserBean>{

    private Context mContext;
    private IHomeView mView;

    public AccountObserver(Context context,IHomeView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(UserBean value) {
        UserEntity entity = new UserEntity(value);
        mContext.getContentResolver().insert(WeiboContract.UserEntry.CONTENT_URI,
                entity.toContentValues());
        mView.setUserView(entity.avatarLargeUrl,entity.userName);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
