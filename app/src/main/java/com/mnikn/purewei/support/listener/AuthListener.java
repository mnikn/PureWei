package com.mnikn.purewei.support.listener;

import android.content.Context;
import android.os.Bundle;

import com.mnikn.mylibrary.util.TextUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.entity.AccountEntity;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class AuthListener implements WeiboAuthListener {

    private Context mContext;

    public AuthListener(Context context){
        mContext = context;
    }

    @Override
    public void onComplete(Bundle bundle) {
        Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
        if (token.isSessionValid()) {
            AccessTokenKeeper.writeAccessToken(mContext, token);
            mContext.getContentResolver().insert(WeiboContract.AccountEntry.CONTENT_URI,
                    new AccountEntity(token).toContentValues());
            ToastUtil.makeToastShort(mContext, "授权成功");
        } else {
            String errorMessage = "授权失败";
            String code = bundle.getString("code");
            if(!TextUtil.isEmpty(code)){
                ToastUtil.makeToastLong(mContext, errorMessage + String.format(",错误代码:%s",code));
            }
            else{
                ToastUtil.makeToastLong(mContext,errorMessage);
            }
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        e.printStackTrace();
    }

    @Override
    public void onCancel() {
        ToastUtil.makeToastShort(mContext, "授权取消");
    }
}
