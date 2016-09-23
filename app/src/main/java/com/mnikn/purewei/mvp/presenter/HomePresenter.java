package com.mnikn.purewei.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.mvp.IHomeView;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.HomeTimelineApi;
import com.mnikn.purewei.support.api.UidApi;
import com.mnikn.purewei.support.api.UserInfoApi;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.support.listener.AccountInfoRequestListener;
import com.mnikn.purewei.support.listener.AuthListener;
import com.mnikn.purewei.support.listener.WeiboRequestListener;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class HomePresenter implements IHomePresenter {

    private IHomeView mView;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;
    private Context mContext;

    private int mPage;



    public HomePresenter(IHomeView view){

        initVariables(view);

        authorize();
        getAccountInfo();
        refresh();
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
                        long uid = DataUtil.jsonToBean(s, UserBean.class).id;
                        new UserInfoApi(mContext,Constant.APP_KEY,mToken,uid)
                                .requestAsync(BaseApi.HTTP_METHOD_GET, new AccountInfoRequestListener(mContext,mView));
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void getHomeWeibo(int requestType) {
        new HomeTimelineApi(mContext,Constant.APP_KEY,mToken,mPage)
                .requestAsync(BaseApi.HTTP_METHOD_GET, new WeiboRequestListener(mContext,mView,requestType));
        ++mPage;
    }

    @Override
    public void refresh() {
        mPage = 1;
        mView.onRefresh();
        getHomeWeibo(Constant.REFRESH);
    }

    @Override
    public void loadMore() {
        mView.onRefresh();
        getHomeWeibo(Constant.LOAD_MORE);
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }
}
