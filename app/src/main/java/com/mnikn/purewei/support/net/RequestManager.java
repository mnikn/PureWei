package com.mnikn.purewei.support.net;

import android.content.Context;
import android.util.Log;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.CommentApi;
import com.mnikn.purewei.support.api.HomeTimelineApi;
import com.mnikn.purewei.support.listener.CommentRequestLisenter;
import com.mnikn.purewei.support.listener.WeiboRequestListener;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RequestManager {

    public static void getHomeWeibo(Context context,IListView view,int requestType,int page){
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        new HomeTimelineApi(context, Constant.APP_KEY,token,page)
                .requestAsync(
                        BaseApi.HTTP_METHOD_GET,
                        new WeiboRequestListener(context,view,requestType));
    }

    public static void getComment(Context context,IListView view,int requestType,int page,long weiboId){
        Log.e("DAS","requestType: " + requestType + " page: " + page);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        new CommentApi(context, Constant.APP_KEY,token,page,weiboId)
                .requestAsync(
                        BaseApi.HTTP_METHOD_GET,
                        new CommentRequestLisenter(context,view,requestType,weiboId));
    }
}
