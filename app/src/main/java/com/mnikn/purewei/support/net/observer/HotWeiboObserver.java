package com.mnikn.purewei.support.net.observer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.mnikn.library.view.net.INetView;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.data.entity.WeiboEntity;
import com.mnikn.purewei.data.entity.WeiboPicsEntity;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.bean.StatusesBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HotWeiboObserver implements Observer<List<StatusesBean>> {

    private Context mContext;
    private INetView mView;
    private int mRequestType;

    public HotWeiboObserver(Context context, INetView view, int requestType){
        mContext = context;
        mView = view;
        mRequestType = requestType;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<StatusesBean> value) {
        ContentResolver resolver = mContext.getContentResolver();

        //刷新时先删除数据
        if(mRequestType == Constant.REFRESH){
            resolver.delete(WeiboContract.WeiboEntry.CONTENT_URI, null, null);
            resolver.delete(WeiboContract.WeiboPicsEntry.CONTENT_URI,null,null);
        }

        //把bean转换成ContentValues,并插入到数据库中
        int size = value.size();
        ContentValues[] weiboValues = new ContentValues[size];
        List<ContentValues> retweetValues = new ArrayList<>();
        for(int i = 0;i < size; ++i){
            WeiboEntity weiboEntity = new WeiboEntity(value.get(i));
            weiboValues[i] = weiboEntity.toContentValues();

            ContentValues[] weiboDetailValues = new WeiboPicsEntity(value.get(i)).toContentValuesArray();
            resolver.bulkInsert(WeiboContract.WeiboPicsEntry.CONTENT_URI,weiboDetailValues);

            resolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                    new UserEntity(value.get(i)).toContentValues(Constant.USER_NORAML));

            //若该微博为转发,就把原微博插入数据库
            if(NumberUtil.notZero(weiboEntity.retweetId)){
                WeiboEntity retweetEntity = new WeiboEntity(value.get(i).retweetedStatus);
                retweetValues.add(retweetEntity.toContentValues());

                resolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                        new UserEntity(value.get(i).retweetedStatus.user).toContentValues(Constant.USER_NORAML));

                ContentValues[] retweetDetailValues = new WeiboPicsEntity(value.get(i).retweetedStatus).toContentValuesArray();
                resolver.bulkInsert(WeiboContract.WeiboPicsEntry.CONTENT_URI, retweetDetailValues);
            }
        }
        resolver.bulkInsert(WeiboContract.WeiboEntry.CONTENT_URI, weiboValues);
        resolver.bulkInsert(WeiboContract.WeiboEntry.CONTENT_URI,
                retweetValues.toArray(new ContentValues[retweetValues.size()]));
    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.makeToastShort(mContext,e.getMessage());
        mView.onLoadMoreFinish();
    }

    @Override
    public void onComplete() {
        switch (mRequestType){
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
}
