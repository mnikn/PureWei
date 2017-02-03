package com.mnikn.purewei.support.net;

import android.content.Context;
import android.os.Bundle;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mnikn.library.utils.Numbers;
import com.mnikn.library.utils.Strings;
import com.mnikn.library.utils.ToastUtils;
import com.mnikn.purewei.data.dao.AccountDao;
import com.mnikn.purewei.data.dao.CommentDao;
import com.mnikn.purewei.data.dao.PictureDao;
import com.mnikn.purewei.data.dao.StatusDao;
import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.model.Account;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.net.observer.AccountObserver;
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

                StatusDao.clear();
                CommentDao.clear();
                PictureDao.clear();
                UserDao.clear();
//                context.getContentResolver().delete(
//                        WeiboContract.UserEntry.CONTENT_URI,
//                        WeiboContract.UserEntry.COLUMN_USER_TYPE + " = ?",
//                        new String[]{"0"});

                Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
                if (token.isSessionValid()) {
                    AccessTokenKeeper.writeAccessToken(context, token);
                    if(!DataUtil.hasAccountId(context, Numbers.stringToLong(token.getUid()))){
                        AccountDao.insertAccount(new Account(token));
                    }
                    ToastUtils.makeToastShort(context, "授权成功");
                    RequestManager.getAccountInfo(
                            context,
                            (IHomeView) context,
                            Numbers.stringToLong(token.getUid()));
                }
                else {
                    String errorMessage = "授权失败";
                    String code = bundle.getString("code");
                    if(!Strings.isBlank(code)){
                        ToastUtils.makeToastLong(context, errorMessage + String.format(",错误代码:%s",code));
                    }
                    else{
                        ToastUtils.makeToastLong(context,errorMessage);
                    }
                }
            }
            @Override
            public void onWeiboException(WeiboException e) {
                e.printStackTrace();
            }
            @Override
            public void onCancel() {
                ToastUtils.makeToastShort(context, "授权取消");
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static Observable getAccountInfo(Context context,IHomeView view,long uid){
        UserService service = sRetrofit.create(UserService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(context);
        Observable observable = service.request(token.getToken(), uid);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AccountObserver(view));
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
}
