package com.mnikn.purewei.support.api;

import android.content.Context;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseApi {

    private static final String LOG_TAG = BaseApi.class.getName();

    public static final String HTTP_METHOD_POST  = "POST";
    public static final String HTTP_METHOD_GET   = "GET";

    /** HTTP 参数 */
    protected static final String KEY_ACCESS_TOKEN = "access_token";

    protected Oauth2AccessToken mAccessToken;
    protected Context mContext;
    protected String mAppKey;

    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param accessToken 访问令牌
     */
    public BaseApi(Context context, String appKey, Oauth2AccessToken accessToken) {
        mContext = context;
        mAppKey = appKey;
        mAccessToken = accessToken;
    }



    public abstract void requestAsync(String httpMethod, RequestListener listener);

    public abstract String requestSync(String httpMethod);

    /**
     * HTTP 同步请求。
     *
     * @param url        请求的地址
     * @param httpMethod 请求方法
     *
     * @return 同步请求后，服务器返回的字符串。
     */
    protected String requestSyncHelper(String url,String httpMethod) {
        WeiboParameters params = getWeiboParameters();
        if (mAccessToken == null
                || TextUtils.isEmpty(url)
                || params == null
                || TextUtils.isEmpty(httpMethod)) {
            LogUtil.e(LOG_TAG, "Argument error!");
            return "";
        }
        return new AsyncWeiboRunner(mContext).request(url, params, httpMethod);
    }

    /**
     * HTTP 异步请求。
     *
     * @param url        请求的地址
     * @param httpMethod 请求方法
     * @param listener   请求后的回调接口
     */
    protected void requestAsyncHelper(String url,String httpMethod,RequestListener listener) {
        WeiboParameters params = getWeiboParameters();
        if (mAccessToken == null
                || TextUtils.isEmpty(url)
                || params == null
                || TextUtils.isEmpty(httpMethod)
                || listener == null) {
            LogUtil.e(LOG_TAG, "Argument error!");
            return;
        }
        new AsyncWeiboRunner(mContext).requestAsync(url, params, httpMethod, listener);
    }

    protected abstract WeiboParameters getWeiboParameters();
}
