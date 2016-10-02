package com.mnikn.purewei.support.observer;

import android.content.Context;

import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.UidApi;
import com.mnikn.purewei.support.api.UserInfoApi;
import com.mnikn.purewei.support.bean.UserBean;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountObserver {

    private Context mContext;
    private IHomeView mView;

    public AccountObserver(Context context,IHomeView view){
        mContext = context;
        mView = view;
    }

    private Observable<UserEntity> observable = Observable.create(new ObservableOnSubscribe<UserEntity>() {
        @Override
        public void subscribe(ObservableEmitter<UserEntity> e) throws Exception {
            Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(mContext);
            String idStr = new UidApi(mContext, Constant.APP_KEY, token).requestSync(BaseApi.HTTP_METHOD_GET);
            long uid = DataUtil.jsonToBean(idStr, UserBean.class).id;

            String infoStr = new UserInfoApi(mContext, Constant.APP_KEY,token,uid).requestSync(BaseApi.HTTP_METHOD_GET);
            UserEntity entity = new UserEntity(DataUtil.jsonToBean(infoStr,UserBean.class));
            mContext.getContentResolver().insert(WeiboContract.UserEntry.CONTENT_URI, entity.toContentValues());

            e.onNext(entity);
            e.onComplete();
        }});

    private Observer<UserEntity> observer = new Observer<UserEntity>() {
        @Override
        public void onSubscribe(Disposable d) {

        }
        @Override
        public void onNext(UserEntity value) {
            mView.setUserView(value.avatarLargeUrl,value.userName);
        }
        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onComplete() {

        }
    };

    public Observable<UserEntity> getObservable() {
        return observable;
    }

    public Observer<UserEntity> getObserver() {
        return observer;
    }
}
