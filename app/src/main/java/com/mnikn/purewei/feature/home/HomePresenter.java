package com.mnikn.purewei.feature.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mnikn.mylibrary.util.TextUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.AccountEntity;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.base.WeiboPresenter;
import com.mnikn.purewei.support.net.RequestManager;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomePresenter extends WeiboPresenter implements IHomePresenter {

    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mToken;

    private int mType = Constant.HOME;

    public HomePresenter(IHomeView view){
        super((Context) view, view);
        
        authorize();
    }

    @Override
    public void authorize() {

        Context context = getContext();
        //从SharePreference中读取token,若失败就请求授权
        mToken = AccessTokenKeeper.readAccessToken(context);
        if (!mToken.isSessionValid()) {
            AuthInfo authInfo = new AuthInfo(context, Constant.APP_KEY, Constant.REDIRECT_URL, null);
            mSsoHandler = new SsoHandler((Activity) context, authInfo);
            mSsoHandler.authorize(new WeiboAuthListener() {
                @Override
                public void onComplete(Bundle bundle) {
                    Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
                    if (token.isSessionValid()) {
                        AccessTokenKeeper.writeAccessToken(getContext(), token);
                        getContext().getContentResolver().insert(WeiboContract.AccountEntry.CONTENT_URI,
                                new AccountEntity(token).toContentValues());
                        ToastUtil.makeToastShort(getContext(), "授权成功");
                        RequestManager.getAccountInfo(getContext(),(IHomeView) getView());
                    }
                    else {
                        String errorMessage = "授权失败";
                        String code = bundle.getString("code");
                        if(!TextUtil.isEmpty(code)){
                            ToastUtil.makeToastLong(getContext(), errorMessage + String.format(",错误代码:%s",code));
                        }
                        else{
                            ToastUtil.makeToastLong(getContext(),errorMessage);
                        }
                    }
                }
                @Override
                public void onWeiboException(WeiboException e) {
                    e.printStackTrace();
                }
                @Override
                public void onCancel() {
                    ToastUtil.makeToastShort(getContext(), "授权取消");
                }
            });
            mToken = AccessTokenKeeper.readAccessToken(context);
        }
        else{
            RequestManager.getAccountInfo(context,((IHomeView) getView()));
        }
    }

    @Override
    public void setWeiboType(int type) {
        mType = type;
    }

    @Override
    public void doRefresh(int page) {
        switch (mType){
            case Constant.HOME:
                RequestManager.getHomeWeibo(
                        getContext(),
                        getView(),
                        Constant.REFRESH,
                        page);
                break;
            case Constant.HOT:
                RequestManager.getHotWeibo(
                        getContext(),
                        getView(),
                        Constant.REFRESH,
                        page);
                break;
        }
    }

    @Override
    public void doLoadMore(int page) {
        switch (mType){
            case Constant.HOME:
                RequestManager.getHomeWeibo(
                        getContext(),
                        getView(),
                        Constant.LOAD_MORE,
                        page);
                break;
            case Constant.HOT:
                RequestManager.getHotWeibo(
                        getContext(),
                        getView(),
                        Constant.LOAD_MORE,
                        page);
                break;
        }
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        if(mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }
}
