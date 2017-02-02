package com.mnikn.purewei.model;

import com.mnikn.library.utils.DateUtils;
import com.mnikn.library.utils.Numbers;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Account {

    public String accessToken;
    public String refreshToken;
    public long id;
    public long expireTime;
    public long expireIn;

    public Account() {}

    public Account(Oauth2AccessToken token) {
        id = Numbers.stringToLong(token.getUid());
        expireIn = DateUtils.getNowDate().getTime();
        expireTime = token.getExpiresTime();
        refreshToken = token.getRefreshToken();
        accessToken = token.getToken();
    }


    public Oauth2AccessToken toOauth2AccessToken() {
        Oauth2AccessToken token = new Oauth2AccessToken();
        token.setUid(Numbers.longToString(id));
        token.setToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setExpiresTime(expireTime);
        token.setExpiresIn(Numbers.longToString(expireIn));

        return token;
    }
}
