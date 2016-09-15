package com.mnikn.purewei.mvp.model;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.WeiboBean;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * 储存微博的信息实体
 */
public class WeiboModel {
    private String text;
    private String source;
    private String retweetText;
    private String retweetUserName;
    private long weiboId;
    private long userId;
    private long reportsCount;
    private long commentsCount;
    private long attitudesCount;
    private long createdTime;

    public WeiboModel(){}

    public WeiboModel(WeiboBean bean, int position){
        fromBean(bean,position);
    }

    public void fromBean(WeiboBean bean,int position){
        weiboId = bean.getStatuses().get(position).getId();
        text = bean.getStatuses().get(position).getText();
        source = TextUtil.cutHerfInfo(bean.getStatuses().get(position).getSource());
        if(bean.getStatuses().get(position).getRetweetedStatus() != null){
            retweetText = bean.getStatuses().get(position).getRetweetedStatus().getText();
            retweetUserName = bean.getStatuses().get(position).getRetweetedStatus().getUser().getScreenName();
        }
        userId = bean.getStatuses().get(position).getUser().getId();
        reportsCount = bean.getStatuses().get(position).getRepostsCount();
        commentsCount = bean.getStatuses().get(position).getCommentsCount();
        attitudesCount = bean.getStatuses().get(position).getAttitudesCount();
        createdTime = DateUtil.dateToLong(bean.getStatuses().get(position).getCreatedAt());
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.WeiboEntry.ID,weiboId);
        values.put(WeiboContract.WeiboEntry.COLUMN_TEXT,text);
        values.put(WeiboContract.WeiboEntry.COLUMN_SOURCE,source);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_TEXT,retweetText);
        values.put(WeiboContract.WeiboEntry.COLUMN_RETWEET_USER_NAME,retweetUserName);
        values.put(WeiboContract.WeiboEntry.COLUMN_USER_ID,userId);
        values.put(WeiboContract.WeiboEntry.COLUMN_ALTITUDES_COUNT,attitudesCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_REPORTS_COUNT,reportsCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_COMMENTS_COUNT,commentsCount);
        values.put(WeiboContract.WeiboEntry.COLUMN_CREATED_TIME,createdTime);
        return values;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
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
}
