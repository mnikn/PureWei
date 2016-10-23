package com.mnikn.purewei.feature.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;
import com.mnikn.purewei.support.net.observer.FavoritesWeiboObserver;
import com.mnikn.purewei.support.net.observer.WeiboObserver;
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

    private int mType = Constant.HOME;

    public HomePresenter(Context context){
        super(context);
    }

    @Override
    protected Observable request() {
        WeiboService service = RequestManager.sRetrofit.create(WeiboService.class);
        int count = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt("key_load_num", 20);
        switch (mType){
            case Constant.HOME:
                return service.getHomeWeibo(mToken.getToken(),getPage(),count);
            case Constant.HOT:
                return service.getHotWeibo(mToken.getToken(), getPage(), count);
            case Constant.FAVORITE:
                return service.getFavoriteWeibo(mToken.getToken(), getPage(), count);
        }
        return null;
    }

    @Override
    protected Observer handleRequest() {
        switch (mType){
            case Constant.HOME:
                if(getPage() == 1) {
                    return new WeiboObserver(getContext(),getView(),Constant.REFRESH);
                }
                return new WeiboObserver(getContext(),getView(),Constant.LOAD_MORE);
            case Constant.HOT:
                if(getPage() == 1) {
                    return new WeiboObserver(getContext(),getView(),Constant.REFRESH);
                }
                return new WeiboObserver(getContext(),getView(),Constant.LOAD_MORE);
            case Constant.FAVORITE:
                if(getPage() == 1) {
                    return new FavoritesWeiboObserver(getContext(),getView(),Constant.REFRESH);
                }
                return new FavoritesWeiboObserver(getContext(),getView(),Constant.LOAD_MORE);
        }
        return null;
    }

    @Override
    protected void onTakeView() {
        super.onTakeView();
        AuthInfo authInfo = new AuthInfo(getContext(),Constant.APP_KEY, Constant.REDIRECT_URL, null);
        mSsoHandler = new SsoHandler((Activity) getContext(),authInfo);

        //从SharePreference中读取token,若失败就请求授权
        mToken = AccessTokenKeeper.readAccessToken(getContext());
        if (!mToken.isSessionValid()) {
            authorize();
            mToken = AccessTokenKeeper.readAccessToken(getContext());
        }
        else{
            RequestManager.getAccountInfo(
                    getContext(),
                    getView(),
                    NumberUtil.stringToLong(mToken.getUid()));
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
