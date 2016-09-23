package com.mnikn.purewei.support.api;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class FriendsTimelineApi extends BaseApi{

    private static final String BASE_URL = "https://api.weibo.com/2/statuses/friends_timeline.json";

    public FriendsTimelineApi(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }

    @Override
    protected WeiboParameters getWeiboParameters() {
        WeiboParameters params = new WeiboParameters(mAppKey);
        params.put(KEY_ACCESS_TOKEN, mAccessToken.getToken());
        return params;
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
