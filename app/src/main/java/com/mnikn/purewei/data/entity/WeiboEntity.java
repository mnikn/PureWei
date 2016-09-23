package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class WeiboEntity {
    public long weiboId;
    public long userId;
    public long retweetId;
    public long createdTime;
    public String text;
    public String source;
    public String retweetText;
    public String retweetUserName;
    public long reportsCount;
    public long commentsCount;
    public long attitudesCount;

    public WeiboEntity() {}
    public WeiboEntity(TimelineBean bean,int position){
        fromBean(bean, position);
    }

    private void fromBean(TimelineBean bean,int position){
        TimelineBean.StatusesBean statusesBean = bean.statuses.get(position);
        weiboId = statusesBean.id;
        userId = statusesBean.user.id;
        if(statusesBean.retweetedStatus != null){
            retweetId = statusesBean.retweetedStatus.id;
            retweetText = statusesBean.retweetedStatus.text;
            retweetUserName = statusesBean.retweetedStatus.user.screenName;
        }
        createdTime = DateUtil.dateToLong(statusesBean.createdAt);
        text = statusesBean.text;
        source = statusesBean.source;
        reportsCount = statusesBean.repostsCount;
        attitudesCount = statusesBean.attitudesCount;
        commentsCount = statusesBean.commentsCount;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.WeiboEntry.COLUMN_WEIBO_ID,weiboId);
        values.put(WeiboContract.WeiboEntry.COLUMN_USER_ID,userId);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_ID,retweetId);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_TEXT,retweetText);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_USER_NAME,retweetUserName);
        values.put(WeiboContract.WeiboEntry.COLUMN_CREATED_TIME,createdTime);
        values.put(WeiboContract.WeiboEntry.COLUMN_TEXT,text);
        values.put(WeiboContract.WeiboEntry.COLUMN_SOURCE,source);
        values.put(WeiboContract.WeiboEntry.COLUMN_REPORTS_COUNT,reportsCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_ATTITUDES_COUNT,attitudesCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_COMMENTS_COUNT,commentsCount);

        return values;
    }
}
