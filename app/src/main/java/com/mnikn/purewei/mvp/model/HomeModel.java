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
    public String weiboId;
    public String reportsCount;
    public String commentsCount;
    public String attitudesCount;
    public String createdTime;
    public String text;
    public String source;
    public String userName;
    public String profileImageUrl;
    public String retweetText;

    public HomeModel(){}

    public HomeModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        if(!NumberUtil.isZero(WeiboContract.WeiboEntry.getRetweetId(cursor))){
            retweetText = WeiboContract.WeiboEntry.getRetweetUserName(cursor) + ":" +
                    WeiboContract.WeiboEntry.getRetweetText(cursor);
        }

        weiboId = NumberUtil.longToString(WeiboContract.WeiboEntry.getWeiboId(cursor));
        reportsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getReportsCount(cursor));
        commentsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getCommentsCount(cursor));
        attitudesCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getAttitudesCount(cursor));
        createdTime = DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor));
        text = WeiboContract.WeiboEntry.getText(cursor);
        source = TextUtil.cutHerfInfo(WeiboContract.WeiboEntry.getSource(cursor));
        userName = WeiboContract.UserEntry.getName(cursor);
    }

}
