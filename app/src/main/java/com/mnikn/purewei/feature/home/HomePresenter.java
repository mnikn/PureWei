package com.mnikn.purewei.feature.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.UidApi;
import com.mnikn.purewei.support.api.UserInfoApi;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.support.listener.AccountInfoRequestListener;
import com.mnikn.purewei.support.listener.AuthListener;
import com.mnikn.purewei.support.net.RequestManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomePresenter implements IHomePresenter {

    private IHomeView mView;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;
    private Context mContext;

    private int mPage;
    private long mUid;

    public HomePresenter(IHomeView view){

        initVariables(view);

        authorize();
        getAccountInfo();
    }

    private void initVariables(IHomeView view){
        mContext = (Context) view;
        mView = view;
        mPage = 1;
    }

    @Override
    public void authorize() {

        //从SharePreference中读取token,若失败就请求授权
        mToken = AccessTokenKeeper.readAccessToken(mContext);
        if (!mToken.isSessionValid()) {
            AuthInfo authInfo = new AuthInfo(mContext, Constant.APP_KEY, Constant.REDIRECT_URL, null);
            mSsoHandler = new SsoHandler((Activity) mContext, authInfo);
            mSsoHandler.authorize(new AuthListener(mContext));
            mToken = AccessTokenKeeper.readAccessToken(mContext);
        }
    }

    @Override
    public void getAccountInfo(){
        //先得到授权用户Id,再根据Id得到用户信息
        new UidApi(mContext,Constant.APP_KEY,mToken)
                .requestAsync(BaseApi.HTTP_METHOD_GET, new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        mUid = DataUtil.jsonToBean(s, UserBean.class).id;
                        new UserInfoApi(mContext,Constant.APP_KEY,mToken,mUid)
                                .requestAsync(BaseApi.HTTP_METHOD_GET, new AccountInfoRequestListener(mContext,mView));
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 1;
        mView.onRefresh();
        RequestManager.getHomeWeibo(
                mContext,
                mView,
                Constant.REFRESH,
                mPage);
        ++mPage;
    }

    @Override
    public void loadMore() {
        mView.onLoadMore();
        RequestManager.getHomeWeibo(
                mContext,
                mView,
                Constant.LOAD_MORE,
                mPage);
        ++mPage;
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }
}
