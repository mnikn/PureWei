package com.mnikn.purewei.support.net;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mnikn.library.view.net.INetView;
import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.TextUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.AccountEntity;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.net.observer.AccountObserver;
import com.mnikn.purewei.support.net.observer.CommentObserver;
import com.mnikn.purewei.support.net.observer.WeiboObserver;
import com.mnikn.purewei.support.net.service.CommentService;
import com.mnikn.purewei.support.net.service.UserService;
import com.mnikn.purewei.support.net.service.WeiboService;
import com.mnikn.purewei.support.util.DataUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RequestManager {

    public static Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(BaseApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static void authorize(SsoHandler ssoHandler, final Context context){
        ssoHandler.authorize(new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {

                context.getContentResolver().delete(WeiboContract.WeiboEntry.CONTENT_URI, null, null);
                context.getContentResolver().delete(WeiboContract.WeiboCommentEntry.CONTENT_URI, null, null);
                context.getContentResolver().delete(WeiboContract.WeiboPicsEntry.CONTENT_URI, null, null);
                context.getContentResolver().delete(
                        WeiboContract.UserEntry.CONTENT_URI,
                        WeiboContract.UserEntry.COLUMN_USER_TYPE + " = ?",
                        new String[]{"0"});

                Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
                if (token.isSessionValid()) {
                    AccessTokenKeeper.writeAccessToken(context, token);
                    if(!DataUtil.hasAccountId(context,NumberUtil.stringToLong(token.getUid()))){
                        context.getContentResolver().insert(WeiboContract.AccountEntry.CONTENT_URI,
                                new AccountEntity(token).toContentValues());
                    }
                    ToastUtil.makeToastShort(context, "授权成功");
                    RequestManager.getAccountInfo(
                            context,
                            (IHomeView) context,
                            NumberUtil.stringToLong(token.getUid()));
                }
                else {
                    String errorMessage = "授权失败";
                    String code = bundle.getString("code");
                    if(!TextUtil.isEmpty(code)){
                        ToastUtil.makeToastLong(context, errorMessage + String.format(",错误代码:%s",code));
                    }
                    else{
                        ToastUtil.makeToastLong(context,errorMessage);
                    }
                }
            }
            @Override
            public void onWeiboException(WeiboException e) {
                e.printStackTrace();
            }
            @Override
            public void onCancel() {
                ToastUtil.makeToastShort(context, "授权取消");
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static Observable getUserWeibo(Context context,INetView view,int requestType,int page){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        int count = PreferenceManager.getDefaultSharedPreferences(context)
                .getInt("key_load_num",20);
        Observable observable = service.getUserWeibo(token.getToken(), page, count);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WeiboObserver(context, view, requestType));
        return observable;
    }

    @SuppressWarnings("unchecked")
    public static Observable getComment(NetPresenter presenter,int requestType,long weiboId){
        CommentService service = sRetrofit.create(CommentService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(presenter.getContext());
        Observable observable = service.getComment(presenter.getPage(), token.getToken(), weiboId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommentObserver(
                        presenter,
                        requestType,
                        weiboId));
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

    public static void getEmotions(Context context){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        service.getEmotions(token.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void createComment(Context context,String content,long id){
        CommentService service = sRetrofit.create(CommentService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        service.createComment(token.getToken(), content, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void createWeibo(Context context, String content){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        service.createWeibo(token.getToken(), content)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void destoryWeibo(Context context,long id){
        WeiboService service = sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        service.destoryWeibo(token.getToken(), id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public static void cancelRequest(Observable observable){
        if(observable == null) return;
        observable.unsubscribeOn(Schedulers.newThread());
    }
}
