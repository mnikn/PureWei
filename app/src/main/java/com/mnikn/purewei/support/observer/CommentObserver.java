package com.mnikn.purewei.support.observer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.data.entity.WeiboCommentEntity;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.api.BaseApi;
import com.mnikn.purewei.support.api.CommentApi;
import com.mnikn.purewei.support.bean.CommentBean;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentObserver {

    private Context mContext;
    private IListView mView;
    private int mType;
    private int mPage;
    private long mWeiboId;

    public CommentObserver(Context context,IListView view,int requestType,int page,long weiboId){
        mContext = context;
        mView = view;
        mType = requestType;
        mPage = page;
        mWeiboId = weiboId;
    }

    private Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(mContext);

            String json = new CommentApi(mContext, Constant.APP_KEY,token,mPage,mWeiboId)
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

            if(mType == Constant.REFRESH){
                resolver.delete(
                        WeiboContract.WeiboCommentEntry.CONTENT_URI,
                        WeiboContract.WeiboCommentEntry.COLUMN_WEIBO_ID + " = ?",
                        new String[]{NumberUtil.longToString(mWeiboId)});
            }

            CommentBean commentBean = DataUtil.jsonToBean(value,CommentBean.class);

            //把bean转换成ContentValues,并插入到数据库中
            int size = commentBean.comments.size();
            ContentValues[] commentValuesArray = new WeiboCommentEntity(commentBean).toContentValuesArray();
            for(int i = 0;i < size; ++i){

                //先查询是否有这位用户信息,没有就插入数据
                long userId = commentBean.comments.get(i).user.id;
                if(!com.mnikn.purewei.support.util.DataUtil.hasUserId(mContext, userId)){
                    resolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                            new UserEntity(commentBean.comments.get(i).user).toContentValues());
                }
            }
            resolver.bulkInsert(WeiboContract.WeiboCommentEntry.CONTENT_URI,commentValuesArray);

            mView.onLoadMoreFinish();
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

    public Observable<String> getObservable() {
        return observable;
    }

    public Observer<String> getObserver() {
        return observer;
    }

}
