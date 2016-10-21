package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.FavoriteBean;
import com.mnikn.purewei.support.bean.StatusesBean;
import com.mnikn.purewei.support.bean.TimelineBean;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboEntity {
    public long weiboId;
    public long userId;
    public long retweetId;
    public long createdTime;
    public String text;
    public String source;
    public long reportsCount;
    public long commentsCount;
    public long attitudesCount;
    public boolean liked;

    public WeiboEntity(TimelineBean bean,int position){
        fromBean(bean, position);
    }
    public WeiboEntity(FavoriteBean bean,int position){
        fromBean(bean,position);
    }
    public WeiboEntity(StatusesBean bean){
        fromBean(bean);
    }

    private void fromBean(TimelineBean bean,int position){
        fromBean(bean.statuses.get(position));
    }

    private void fromBean(FavoriteBean bean,int position){
        fromBean(bean.favorites.get(position).status);
    }

    private void fromBean(FavoriteBean.FavoritesBean.StatusBean bean){
        weiboId = bean.id;
        userId = bean.user.id;
        if(bean.retweetedStatus != null && bean.retweetedStatus.user != null){
            retweetId = bean.retweetedStatus.id;
        }
        createdTime = DateUtil.dateToLong(bean.createdAt);
        text = bean.text;
        source = bean.source;
        reportsCount = bean.repostsCount;
        attitudesCount = bean.attitudesCount;
        commentsCount = bean.commentsCount;
        liked = bean.liked;
    }

    private void fromBean(StatusesBean bean){
        weiboId = bean.id;
        userId = bean.user.id;
        if(bean.retweetedStatus != null && bean.retweetedStatus.user != null){
            retweetId = bean.retweetedStatus.id;
        }
        createdTime = DateUtil.dateToLong(bean.createdAt);
        text = bean.text;
        source = bean.source;
        reportsCount = bean.repostsCount;
        attitudesCount = bean.attitudesCount;
        commentsCount = bean.commentsCount;
        liked = bean.liked;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.WeiboEntry.COLUMN_WEIBO_ID,weiboId);
        values.put(WeiboContract.WeiboEntry.COLUMN_USER_ID,userId);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_ID,retweetId);
        values.put(WeiboContract.WeiboEntry.COLUMN_CREATED_TIME,createdTime);
        values.put(WeiboContract.WeiboEntry.COLUMN_TEXT,text);
        values.put(WeiboContract.WeiboEntry.COLUMN_SOURCE,source);
        values.put(WeiboContract.WeiboEntry.COLUMN_REPORTS_COUNT,reportsCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_ATTITUDES_COUNT,attitudesCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_COMMENTS_COUNT,commentsCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_LIKED,liked);

        return values;
    }
}
