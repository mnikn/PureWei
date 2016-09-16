package com.mnikn.purewei.support.entity;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class AccountEntity {
    private long uid;
    private String token;

    public AccountEntity() {}

    public AccountEntity(Oauth2AccessToken accessToken){
        fromToken(accessToken);
    }

    private void fromToken(Oauth2AccessToken accessToken){
        uid = NumberUtil.stringToLong(accessToken.getUid());
        token = accessToken.getToken();
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.AccountEntry.COLUMN_UID,uid);
        values.put(WeiboContract.AccountEntry.COLUMN_TOKEN,token);

        return values;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
