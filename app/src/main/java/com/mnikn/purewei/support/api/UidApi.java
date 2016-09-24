package com.mnikn.purewei.support.api;


import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UidApi extends BaseApi{
    public static final String BASE_URL = "https://api.weibo.com/2/account/get_uid.json";

    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param context
     * @param appKey
     * @param accessToken 访问令牌
     */
    public UidApi(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }

    @Override
    protected WeiboParameters getWeiboParameters() {
        WeiboParameters parameters = new WeiboParameters(mAppKey);
        parameters.put(KEY_ACCESS_TOKEN,mAccessToken.getToken());
        return parameters;
    }

    @Override
    public void requestAsync(String httpMethod, RequestListener listener) {
        super.requestAsyncHelper(BASE_URL, httpMethod, listener);
    }

    @Override
    public String requestSync(String httpMethod) {
        return super.requestSyncHelper(BASE_URL, httpMethod);
    }
}
