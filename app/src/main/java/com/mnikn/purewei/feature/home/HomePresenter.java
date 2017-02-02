package com.mnikn.purewei.feature.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mnikn.library.utils.Numbers;
import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constants;
import com.mnikn.purewei.support.net.RequestManager;
import com.mnikn.purewei.support.net.observer.FavoritesWeiboObserver;
import com.mnikn.purewei.support.net.observer.StatusObserver;
import com.mnikn.purewei.support.net.service.WeiboService;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomePresenter extends NetPresenter<IHomeView> implements IHomePresenter {

    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;

    private int mType = Constants.HOME;

    public HomePresenter(Context context){
        super(context);
    }

    @Override
    protected Observable request() {
        WeiboService service = RequestManager.sRetrofit.create(WeiboService.class);
        int count = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt("key_load_num", 20);
        switch (mType){
            case Constants.HOME:
                return service.getHomeWeibo(mToken.getToken(),getPage(),count);
            case Constants.HOT:
                return service.getHotWeibo(mToken.getToken(), getPage(), count);
            case Constants.FAVORITE:
                return service.getFavoriteWeibo(mToken.getToken(), getPage(), count);
        }
        return null;
    }

    @Override
    protected Observer handleRequest() {
        switch (mType){
            case Constants.HOME:
                if(getPage() == 1) {
                    return new StatusObserver(getContext(),getView(), Constants.REFRESH);
                }
                return new StatusObserver(getContext(),getView(), Constants.LOAD_MORE);
            case Constants.HOT:
                if(getPage() == 1) {
                    return new StatusObserver(getContext(),getView(), Constants.REFRESH);
                }
                return new StatusObserver(getContext(),getView(), Constants.LOAD_MORE);
            case Constants.FAVORITE:
                if(getPage() == 1) {
                    return new FavoritesWeiboObserver(getContext(),getView(), Constants.REFRESH);
                }
                return new FavoritesWeiboObserver(getContext(),getView(), Constants.LOAD_MORE);
        }
        return null;
    }

    @Override
    protected void onTakeView() {
        super.onTakeView();
        AuthInfo authInfo = new AuthInfo(getContext(), Constants.APP_KEY, Constants.REDIRECT_URL, null);
        mSsoHandler = new SsoHandler((Activity) getContext(),authInfo);

        //从SharePreference中读取token,若失败就请求授权
        mToken = AccessTokenKeeper.readAccessToken(getContext());
        if (!mToken.isSessionValid()) {
            authorize();
            mToken = AccessTokenKeeper.readAccessToken(getContext());
        }
        else{
            Log.e("Dsa",mToken.getToken() + " " + mToken.getUid());
            RequestManager.getAccountInfo(
                    getContext(),
                    getView(),
                    Numbers.stringToLong(mToken.getUid()));
            refresh();
        }
    }

    @Override
    public void authorize() {
        RequestManager.authorize(mSsoHandler, getContext());
    }

    @Override
    public void setWeiboType(int type) {
        mType = type;
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }
}
