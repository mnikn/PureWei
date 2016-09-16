package com.mnikn.purewei.support.entity;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class WeiboEntity {
    private long weiboId;
    private long userId;
    private long retweetId;
    private long createdTime;
    private String text;
    private String source;
    private String retweetText;
    private String retweetUserName;
    private long reportsCount;
    private long commentsCount;
    private long attitudesCount;

    public WeiboEntity() {}
    public WeiboEntity(TimelineBean bean,int position){
        fromBean(bean, position);
    }

    private void fromBean(TimelineBean bean,int position){
        TimelineBean.StatusesBean statusesBean = bean.getStatuses().get(position);
        weiboId = statusesBean.getId();
        userId = statusesBean.getUser().getId();
        if(statusesBean.getRetweetedStatus() != null){
            retweetId = statusesBean.getRetweetedStatus().getId();
            retweetText = statusesBean.getRetweetedStatus().getText();
            retweetUserName = statusesBean.getRetweetedStatus().getUser().getScreenName();
        }
        createdTime = DateUtil.dateToLong(statusesBean.getCreatedAt());
        text = statusesBean.getText();
        source = statusesBean.getSource();
        reportsCount = statusesBean.getRepostsCount();
        attitudesCount = statusesBean.getAttitudesCount();
        commentsCount = statusesBean.getCommentsCount();
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

    public long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(long weiboId) {
        this.weiboId = weiboId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRetweetId() {
        return retweetId;
    }

    public void setRetweetId(long retweetId) {
        this.retweetId = retweetId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRetweetText() {
        return retweetText;
    }

    public void setRetweetText(String retweetText) {
        this.retweetText = retweetText;
    }

    public String getRetweetUserName() {
        return retweetUserName;
    }

    public void setRetweetUserName(String retweetUserName) {
        this.retweetUserName = retweetUserName;
    }

    public long getReportsCount() {
        return reportsCount;
    }

    public void setReportsCount(long reportsCount) {
        this.reportsCount = reportsCount;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public long getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(long attitudesCount) {
        this.attitudesCount = attitudesCount;
    }
}
