package com.mnikn.purewei.model;

import android.database.Cursor;

import com.mnikn.mylibrary.mvp.IModel;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserModel implements IModel {
    public String userName;
    public String description;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;
    public String followersCount;
    public String friendsCount;
    public String weiboCount;

    public UserModel(){}

    public UserModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        userName = WeiboContract.UserEntry.getName(cursor);
        description = WeiboContract.UserEntry.getDescription(cursor);
        profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
        avatarLargeUrl = WeiboContract.UserEntry.getAvatarLargeUrl(cursor);
        avatarHdUrl = WeiboContract.UserEntry.getAvatarHdUrl(cursor);
        followersCount = NumberUtil.longToString(WeiboContract.UserEntry.getFollowerCount(cursor));
        friendsCount = NumberUtil.longToString(WeiboContract.UserEntry.getFriendsCount(cursor));
        weiboCount = NumberUtil.longToString(WeiboContract.UserEntry.getWeiboCount(cursor));
    }
}
