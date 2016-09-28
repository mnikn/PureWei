package com.mnikn.purewei.support.listener;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.data.entity.WeiboCommentEntity;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.bean.CommentBean;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentRequestLisenter implements RequestListener {

    private static final String LOG_TAG = WeiboRequestListener.class.getName();

    private Context mContext;
    private ContentResolver mResolver;
    private IListView mView;
    private int mRequestType;
    private long mWeiboId;

    public CommentRequestLisenter(Context context, IListView view,int requestType,long weiboId){
        mContext = context;
        mView = view;
        mRequestType = requestType;
        mWeiboId = weiboId;
        mResolver = mContext.getContentResolver();
    }

    @Override
    public void onComplete(String s) {

        if(mRequestType == Constant.REFRESH){
            mResolver.delete(
                    WeiboContract.WeiboCommentEntry.CONTENT_URI,
                    WeiboContract.WeiboCommentEntry.COLUMN_WEIBO_ID + " = ?",
                    new String[]{NumberUtil.longToString(mWeiboId)});
        }

        CommentBean commentBean = DataUtil.jsonToBean(s,CommentBean.class);

        //把bean转换成ContentValues,并插入到数据库中
        int size = commentBean.comments.size();
        ContentValues[] commentValuesArray = new WeiboCommentEntity(commentBean).toContentValuesArray();
        for(int i = 0;i < size; ++i){

            //先查询是否有这位用户信息,没有就插入数据
            long userId = commentBean.comments.get(i).user.id;
            if(!com.mnikn.purewei.support.util.DataUtil.hasUserId(mContext, userId)){
                mResolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                        new UserEntity(commentBean.comments.get(i).user).toContentValues());
            }
        }
        mResolver.bulkInsert(WeiboContract.WeiboCommentEntry.CONTENT_URI,commentValuesArray);

        mView.onLoadMoreFinish();
    }

    @Override
    public void onWeiboException(WeiboException e) {
        Log.e("S",e.getMessage());
    }
}
