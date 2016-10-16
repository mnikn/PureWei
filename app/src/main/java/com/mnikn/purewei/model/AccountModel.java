package com.mnikn.purewei.model;

import android.database.Cursor;

import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountModel{

    public String accessToken;
    public String refreshToken;
    public long uid;
    public long expireTime;
    public long expireIn;

    public AccountModel() {}
    public AccountModel(Cursor cursor) {
        accessToken = WeiboContract.AccountEntry.getAccessToken(cursor);
        refreshToken = WeiboContract.AccountEntry.getRefreshToken(cursor);
        uid = WeiboContract.AccountEntry.getUid(cursor);
        expireTime = WeiboContract.AccountEntry.getExpiresTime(cursor);
        expireIn = WeiboContract.AccountEntry.getExpiresIn(cursor);
    }

    public static Oauth2AccessToken toOauth2AccessToken(AccountModel model) {
        Oauth2AccessToken accessToken = new Oauth2AccessToken();
        accessToken.setToken(model.accessToken);
        accessToken.setRefreshToken(model.refreshToken);
        accessToken.setExpiresTime(model.expireTime);
        accessToken.setExpiresIn(NumberUtil.longToString(model.expireIn));

        return accessToken;
    }
}
