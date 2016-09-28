package com.mnikn.purewei.model;

import android.database.Cursor;

import com.mnikn.mylibrary.mvp.IModel;
import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboModel implements IModel{
    public long weiboId;
    public long userId;
    public long retweedId;
    public String reportsCount;
    public String commentsCount;
    public String attitudesCount;
    public String createdTime;
    public String text;
    public String source;
    public String userName;
    public String profileImageUrl;
    public String retweetText;

    public WeiboModel(){}

    public WeiboModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        if(cursor == null) return;

        if(!NumberUtil.isZero(WeiboContract.WeiboEntry.getRetweetId(cursor))){
            retweedId = WeiboContract.WeiboEntry.getRetweetId(cursor);
            retweetText = WeiboContract.WeiboEntry.getRetweetUserName(cursor) + ":" +
                    WeiboContract.WeiboEntry.getRetweetText(cursor);
        }

        weiboId = WeiboContract.WeiboEntry.getWeiboId(cursor);
        userId = WeiboContract.WeiboEntry.getUserId(cursor);
        reportsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getReportsCount(cursor));
        commentsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getCommentsCount(cursor));
        attitudesCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getAttitudesCount(cursor));
        createdTime = DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor));
        text = WeiboContract.WeiboEntry.getText(cursor);
        source = TextUtil.cutHerfInfo(WeiboContract.WeiboEntry.getSource(cursor));
        userName = WeiboContract.UserEntry.getName(cursor);
        profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
    }

}
