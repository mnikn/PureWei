package com.mnikn.purewei.support.net;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.net.observer.AccountObserver;
import com.mnikn.purewei.support.net.observer.CommentObserver;
import com.mnikn.purewei.support.net.observer.WeiboObserver;
import com.mnikn.purewei.support.net.service.CommentService;
import com.mnikn.purewei.support.net.service.UserService;
import com.mnikn.purewei.support.net.service.WeiboService;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RequestManager {

    private static Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(BaseApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @SuppressWarnings("unchecked")
    public static Observable getHomeWeibo(Context context,IListView view,int requestType,int page){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        Observable observable = service.getHomeWeibo(page, token.getToken());
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WeiboObserver(context,view,requestType));
        return observable;
    }

    @SuppressWarnings("unchecked")
    public static Observable getHotWeibo(Context context,IListView view,int requestType,int page){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        Observable observable = service.getHotWeibo(page, token.getToken());
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WeiboObserver(context,view,requestType));
        return observable;
    }
    @SuppressWarnings("unchecked")
    public static Observable getComment(Context context,IListView view,int requestType,int page,long weiboId){
        CommentService service = sRetrofit.create(CommentService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        Observable observable = service.request(page, token.getToken(), weiboId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommentObserver(context,view,page,requestType,weiboId));
        return observable;
    }

    @SuppressWarnings("unchecked")
    public static Observable getAccountInfo(Context context,IHomeView view,long uid){
        UserService service = sRetrofit.create(UserService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        Observable observable = service.request(token.getToken(), uid);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AccountObserver(context, view));
        return observable;
    }

    public static void cancelRequest(Observable observable){
        if(observable == null) return;
        observable.unsubscribeOn(Schedulers.newThread());
    }
}
