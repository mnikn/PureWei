package com.mnikn.purewei.model;

import android.database.Cursor;

import com.mnikn.library.utils.Numbers;
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

    public Oauth2AccessToken toOauth2AccessToken() {
        Oauth2AccessToken token = new Oauth2AccessToken();
        token.setUid(Numbers.longToString(uid));
        token.setToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setExpiresTime(expireTime);
        token.setExpiresIn(Numbers.longToString(expireIn));

        return token;
    }
}
