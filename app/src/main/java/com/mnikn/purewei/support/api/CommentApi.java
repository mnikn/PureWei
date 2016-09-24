package com.mnikn.purewei.support.api;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentApi extends BaseApi {

    private static final String BASE_URL = "https://api.weibo.com/2/comments/show.json";

    private static final String KEY_WEIBO_ID = "weibo_id";

    private long mWeiboId;

    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param context
     * @param appKey
     * @param accessToken 访问令牌
     */
    public CommentApi(Context context, String appKey, Oauth2AccessToken accessToken,long weiboId) {
        super(context, appKey, accessToken);
        mWeiboId = weiboId;
    }

    @Override
    public void requestAsync(String httpMethod, RequestListener listener) {
        super.requestAsyncHelper(BASE_URL,httpMethod,listener);
    }

    @Override
    public String requestSync(String httpMethod) {
        return super.requestSyncHelper(BASE_URL, httpMethod);
    }

    @Override
    protected WeiboParameters getWeiboParameters() {
        WeiboParameters params = new WeiboParameters(mAppKey);
        params.put(KEY_ACCESS_TOKEN, mAccessToken.getToken());
        params.put(KEY_WEIBO_ID,mWeiboId);
        return null;
    }
}
