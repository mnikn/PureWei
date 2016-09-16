package com.mnikn.purewei.mvp.model;

import android.database.Cursor;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * 储存微博的信息实体
 */
public class HomeModel {
    private long reportsCount;
    private long commentsCount;
    private long attitudesCount;
    private String createdTime;
    private String text;
    private String source;
    private String userName;
    private String profileImageUrl;
    private String retweetText;



    public HomeModel(){}

    public HomeModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        if(!NumberUtil.isZero(WeiboContract.WeiboEntry.getRetweetId(cursor))){
            retweetText = WeiboContract.WeiboEntry.getRetweetUserName(cursor) + ":" +
                    WeiboContract.WeiboEntry.getRetweetText(cursor);
        }
        reportsCount = WeiboContract.WeiboEntry.getReportsCount(cursor);
        commentsCount = WeiboContract.WeiboEntry.getCommentsCount(cursor);
        attitudesCount = WeiboContract.WeiboEntry.getAttitudesCount(cursor);
        createdTime = DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor));
        text = WeiboContract.WeiboEntry.getText(cursor);
        source = TextUtil.cutHerfInfo(WeiboContract.WeiboEntry.getSource(cursor));
        userName = WeiboContract.UserEntry.getName(cursor);
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getRetweetText() {
        return retweetText;
    }

    public void setRetweetText(String retweetText) {
        this.retweetText = retweetText;
    }
}
