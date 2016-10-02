package com.mnikn.purewei.support.net;

import android.content.Context;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.observer.AccountObserver;
import com.mnikn.purewei.support.observer.CommentObserver;
import com.mnikn.purewei.support.observer.WeiboObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RequestManager {

    public static void getAccountInfo(Context context,IHomeView view){
        AccountObserver accountObserver = new AccountObserver(context,view);
        accountObserver.getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accountObserver.getObserver());
    }

    public static void getHomeWeibo(Context context,IListView view,int requestType,int page){
        WeiboObserver weiboObserver = new WeiboObserver(context,view,requestType,page);
        weiboObserver.getHomeObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weiboObserver.getObserver());
    }

    public static void getHotWeibo(Context context,IListView view,int requestType,int page){
        WeiboObserver weiboObserver = new WeiboObserver(context,view,requestType,page);
        weiboObserver.getHotObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weiboObserver.getObserver());
    }

    public static void getComment(Context context,IListView view,int requestType,int page,long weiboId){
        CommentObserver commentObserver = new CommentObserver(context,view,requestType,page,weiboId);
        commentObserver.getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentObserver.getObserver());
    }
}
