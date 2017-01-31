package com.mnikn.purewei.model;

import android.database.Cursor;

import com.mnikn.library.utils.DateUtils;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentModel {
    private long weiboId;
    private long userId;
    public String createdTime;
    public String text;
    public String source;
    public String userName;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;

    public CommentModel(){}

    public CommentModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){

        weiboId = WeiboContract.WeiboCommentEntry.getWeiboId(cursor);
        userId = WeiboContract.WeiboCommentEntry.getUserId(cursor);
        createdTime = DateUtils.getShowDay(WeiboContract.WeiboCommentEntry.getCommentTime(cursor));
        text = WeiboContract.WeiboCommentEntry.getCommentText(cursor);
        source = TextUtil.cutHerfInfo(WeiboContract.WeiboCommentEntry.getCommentSource(cursor));
        userName = WeiboContract.UserEntry.getName(cursor);
        profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
        avatarLargeUrl = WeiboContract.UserEntry.getAvatarLargeUrl(cursor);
        avatarHdUrl = WeiboContract.UserEntry.getAvatarHdUrl(cursor);
    }
}
