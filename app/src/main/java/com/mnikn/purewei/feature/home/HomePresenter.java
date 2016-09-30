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
import com.mnikn.purewei.support.base.WeiboPresenter;
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
public class HomePresenter extends WeiboPresenter implements IHomePresenter {

    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;

    private long mUid;

    public HomePresenter(IHomeView view){
        super((Context) view, view);
        
        authorize();
        getAccountInfo();
    }

    @Override
    public void authorize() {

        Context context = getContext();
        //从SharePreference中读取token,若失败就请求授权
        mToken = AccessTokenKeeper.readAccessToken(context);
        if (!mToken.isSessionValid()) {
            AuthInfo authInfo = new AuthInfo(context, Constant.APP_KEY, Constant.REDIRECT_URL, null);
            mSsoHandler = new SsoHandler((Activity) context, authInfo);
            mSsoHandler.authorize(new AuthListener(context));
            mToken = AccessTokenKeeper.readAccessToken(context);
        }
    }

    @Override
    public void getAccountInfo(){
        final Context context = getContext();
        //先得到授权用户Id,再根据Id得到用户信息
        new UidApi(context,Constant.APP_KEY,mToken)
                .requestAsync(BaseApi.HTTP_METHOD_GET, new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        mUid = DataUtil.jsonToBean(s, UserBean.class).id;
                        new UserInfoApi(context,Constant.APP_KEY,mToken,mUid)
                                .requestAsync(BaseApi.HTTP_METHOD_GET, new AccountInfoRequestListener(context,(IHomeView) getView()));
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void doRefresh(int page) {
        RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.REFRESH,
                page);
    }

    @Override
    public void doLoadMore(int page) {
        RequestManager.getHomeWeibo(
                getContext(),
                getView(),
                Constant.LOAD_MORE,
                page);
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }


}
