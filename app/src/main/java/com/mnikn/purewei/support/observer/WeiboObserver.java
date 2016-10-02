package com.mnikn.purewei.support.observer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.data.entity.WeiboEntity;
import com.mnikn.purewei.data.entity.WeiboPicsEntity;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.HomeTimelineApi;
import com.mnikn.purewei.support.api.PublicTimelineApi;
import com.mnikn.purewei.support.bean.TimelineBean;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboObserver {

    private Context mContext;
    private IListView mView;
    private int mType;
    private int mPage;

    public WeiboObserver(Context context,IListView view,int requestType,int page){
        mContext = context;
        mView = view;
        mType = requestType;
        mPage = page;
    }

    private Observable<String> homeObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(mContext);

            String json = new HomeTimelineApi(mContext, Constant.APP_KEY,token,mPage)
                    .requestSync(BaseApi.HTTP_METHOD_GET);

            e.onNext(json);
            e.onComplete();
        }});

    private Observable<String> hotObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(mContext);

            String json = new PublicTimelineApi(mContext, Constant.APP_KEY,token,mPage)
                    .requestSync(BaseApi.HTTP_METHOD_GET);

            e.onNext(json);
            e.onComplete();
        }});

    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }
        @Override
        public void onNext(String value) {

            ContentResolver resolver = mContext.getContentResolver();

            //刷新时先删除数据
            if(mType == Constant.REFRESH){
                resolver.delete(WeiboContract.WeiboEntry.CONTENT_URI, null, null);
                resolver.delete(WeiboContract.WeiboPicsEntry.CONTENT_URI,null,null);
                resolver.delete(WeiboContract.WeiboCommentEntry.CONTENT_URI,null,null);
                resolver.delete(WeiboContract.UserEntry.CONTENT_URI,null,null);
            }

            TimelineBean timelineBean = DataUtil.jsonToBean(value,TimelineBean.class);

            //把bean转换成ContentValues,并插入到数据库中
            int size = timelineBean.statuses.size();
            ContentValues[] weiboValues = new ContentValues[size];
            for(int i = 0;i < size; ++i){
                weiboValues[i] = new WeiboEntity(timelineBean,i).toContentValues();

                ContentValues[] weiboDetailValues = new WeiboPicsEntity(timelineBean,i).toContentValuesArray();
                resolver.bulkInsert(WeiboContract.WeiboPicsEntry.CONTENT_URI,weiboDetailValues);

                //先查询是否有这位用户信息,没有就插入数据
                long userId = timelineBean.statuses.get(i).user.id;
                if(!com.mnikn.purewei.support.util.DataUtil.hasUserId(mContext, userId)){
                    resolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                            new UserEntity(timelineBean.statuses.get(i)).toContentValues());
                }
            }
            resolver.bulkInsert(WeiboContract.WeiboEntry.CONTENT_URI, weiboValues);
        }
        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onComplete() {
            switch (mType){
                case Constant.REFRESH:
                    mView.onRefreshFinish();
                    break;
                case Constant.LOAD_MORE:
                    mView.onLoadMoreFinish();
                    break;
                default:
                    mView.onLoadMoreFinish();
            }
        }
    };

    public Observer<String> getObserver() {
        return observer;
    }

    public Observable<String> getHomeObservable() {
        return homeObservable;
    }

    public Observable<String> getHotObservable() {
        return hotObservable;
    }
}
