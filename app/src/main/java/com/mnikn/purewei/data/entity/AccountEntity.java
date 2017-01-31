package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.data.WeiboContract;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.GregorianCalendar;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountEntity {
    private long uid;
    private long expireTime;
    private long expireIn;
    private String accessToken;
    private String refreshToken;

    public AccountEntity(Oauth2AccessToken accessToken){
        fromToken(accessToken);
    }

    private void fromToken(Oauth2AccessToken accessToken){
        uid = Numbers.stringToLong(accessToken.getUid());
        expireTime = accessToken.getExpiresTime();
        this.accessToken = accessToken.getToken();
        refreshToken = accessToken.getRefreshToken();

        expireIn = new GregorianCalendar().getTimeInMillis();
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.AccountEntry.COLUMN_UID,uid);
        values.put(WeiboContract.AccountEntry.COLUMN_EXPIRES_TIME,expireTime);
        values.put(WeiboContract.AccountEntry.COLUMN_EXPIRES_IN,expireIn);
        values.put(WeiboContract.AccountEntry.COLUMN_ACCESS_TOKEN,accessToken);
        values.put(WeiboContract.AccountEntry.COLUMN_REFRESH_TOKEN,refreshToken);

        return values;
    }
}
