package com.mnikn.purewei.support.listener;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.mvp.IHomeView;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.bean.TimelineBean;
import com.mnikn.purewei.support.entity.UserEntity;
import com.mnikn.purewei.support.entity.WeiboEntity;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class WeiboRequestListener implements RequestListener {

    private static final String LOG_TAG = WeiboRequestListener.class.getName();

    private Context mContext;
    private ContentResolver mResolver;
    private IHomeView mView;
    private int mType;

    public WeiboRequestListener(Context context, IHomeView view,int requestType){
        mContext = context;
        mView = view;
        mType = requestType;
        mResolver = mContext.getContentResolver();

    }

    @Override
    public void onComplete(String s) {

        //刷新时先删除数据
        if(mType == Constant.REFRESH){
            mResolver.delete(WeiboContract.WeiboEntry.CONTENT_URI, null, null);
            mResolver.delete(WeiboContract.UserEntry.CONTENT_URI,null,null);
        }

        TimelineBean timelineBean = DataUtil.jsonToBean(s,TimelineBean.class);

        //把bean转换成ContentValues,并插入到数据库中
        int size = timelineBean.getStatuses().size();
        ContentValues[] weiboValues = new ContentValues[size];
        for(int i = 0;i < size; ++i){
            weiboValues[i] = new WeiboEntity(timelineBean,i).toContentValues();

            //先查询是否有这位用户信息,没有就插入数据
            long userId = timelineBean.getStatuses().get(i).getUser().getId();
            if(!com.mnikn.purewei.support.util.DataUtil.hasUserId(mContext, userId)){
                mResolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                        new UserEntity(timelineBean,i).toContentValues());
            }
        }
        mResolver.bulkInsert(WeiboContract.WeiboEntry.CONTENT_URI, weiboValues);

        switch (mType){
            case Constant.REFRESH:
                mView.onRefreshFinish();
                break;
            case Constant.LOAD_MORE:
                mView.onLoadMoreFinish();
                break;
            default:
                Log.e(LOG_TAG,"No such a type:" + mType);
                mView.onLoadMoreFinish();
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        e.printStackTrace();
    }
}
