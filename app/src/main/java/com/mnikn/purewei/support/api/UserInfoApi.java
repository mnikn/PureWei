package com.mnikn.purewei.support.api;

import android.content.Context;
import android.util.Log;

import com.mnikn.mylibrary.util.TextUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class UserInfoApi extends BaseApi {


    public static final String BASE_URL = "https://api.weibo.com/2/users/show.json";

    private static final String KEY_UID = "uid";
    private static final String KEY_SCREEN_NAME = "screen_name";


    private static final String LOG_TAG = UserInfoApi.class.getName();

    private long mUid;
    private String mUsername;

    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param context
     * @param appKey
     * @param accessToken 访问令牌
     */
    public UserInfoApi(Context context, String appKey, Oauth2AccessToken accessToken,long uid) {
        super(context, appKey, accessToken);
        mUid = uid;
    }

    public UserInfoApi(Context context, String appKey, Oauth2AccessToken accessToken,String userName) {
        super(context, appKey, accessToken);
        mUsername = userName;
    }

    @Override
    protected WeiboParameters getWeiboParameters() {
        WeiboParameters parameters = new WeiboParameters(mAppKey);
        parameters.put(KEY_ACCESS_TOKEN,mAccessToken.getToken());
        if(mUid != 0){
            parameters.put(KEY_UID,mUid);
        }
        else if(!TextUtil.isEmpty(mUsername)){
            parameters.put(KEY_SCREEN_NAME,mUsername);
        }
        else{
            Log.e(LOG_TAG,"Wrong parameter");
        }

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
