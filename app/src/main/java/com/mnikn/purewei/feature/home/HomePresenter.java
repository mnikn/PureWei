package com.mnikn.purewei.feature.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomePresenter extends NetPresenter<IHomeView> implements IHomePresenter {

    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;

    private Observable homeWeiboObservable;
    private Observable hotWeiboObservable;

    private int mType = Constant.HOME;

    public HomePresenter(Context context){
        super(context);
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
        RequestManager.authorize(mSsoHandler,getContext());
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

    public void cancelLoading() {
        mIsLoading = false;
        RequestManager.cancelRequest(homeWeiboObservable);
        RequestManager.cancelRequest(hotWeiboObservable);
    }

    @Override
    protected void request(int page) {
        if(page == 1){
            switch (mType){
                case Constant.HOME:
                    homeWeiboObservable = RequestManager.getHomeWeibo(
                            getContext(),
                            getView(),
                            Constant.REFRESH,
                            page);
                    break;
                case Constant.HOT:
                    hotWeiboObservable = RequestManager.getHotWeibo(
                            getContext(),
                            getView(),
                            Constant.REFRESH,
                            page);
                    break;
                case Constant.FAVORITE:
                    hotWeiboObservable = RequestManager.getFavoritesWeibo(
                            getContext(),
                            getView(),
                            Constant.REFRESH,
                            page);
                    break;
            }
        }
        else{
            switch (mType){
                case Constant.HOME:
                    homeWeiboObservable = RequestManager.getHomeWeibo(
                            getContext(),
                            getView(),
                            Constant.LOAD_MORE,
                            page);
                    break;
                case Constant.HOT:
                    hotWeiboObservable = RequestManager.getHotWeibo(
                            getContext(),
                            getView(),
                            Constant.LOAD_MORE,
                            page);
                    break;
                case Constant.FAVORITE:
                    hotWeiboObservable = RequestManager.getFavoritesWeibo(
                            getContext(),
                            getView(),
                            Constant.LOAD_MORE,
                            page);
                    break;
            }
        }
    }
}
