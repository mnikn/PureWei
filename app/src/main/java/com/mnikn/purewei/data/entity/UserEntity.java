package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;
import com.mnikn.purewei.support.bean.UserBean;
/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserEntity {
    public long userId;
    public String userName;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;
    public String description;
    public long followersCount;
    public long friendsCount;
    public long weiboCount;


    public UserEntity() {}
    public UserEntity(TimelineBean.StatusesBean bean){
        fromTimelineBean(bean);
    }
    public UserEntity(UserBean userBean){
        fromUserBean(userBean);
    }

    private void fromTimelineBean(TimelineBean.StatusesBean bean){
        userId = bean.user.id;
        userName = bean.user.screenName;
        profileImageUrl = bean.user.profileImageUrl;
        avatarLargeUrl = bean.user.avatarLarge;
        avatarHdUrl = bean.user.avatarHd;
        description = bean.user.description;
        followersCount = bean.user.followersCount;
        friendsCount = bean.user.friendsCount;
        weiboCount = bean.user.statusesCount;
    }

    private void fromUserBean(UserBean bean){
        userId = bean.id;
        userName = bean.screenName;
        profileImageUrl = bean.profileImageUrl;
        avatarLargeUrl = bean.avatarLarge;
        avatarHdUrl = bean.avatarHd;
        description = bean.description;
        followersCount = bean.followersCount;
        friendsCount = bean.friendsCount;
        weiboCount = bean.statusesCount;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.UserEntry.COLUMN_USER_ID,userId);
        values.put(WeiboContract.UserEntry.COLUMN_NAME,userName);
        values.put(WeiboContract.UserEntry.COLUMN_PROFILE_IMAGE_URL,profileImageUrl);
        values.put(WeiboContract.UserEntry.COLUMN_AVATAR_LARGE_URL,avatarLargeUrl);
        values.put(WeiboContract.UserEntry.COLUMN_AVATAR_HD_URL,avatarHdUrl);
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION,description);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT,followersCount);
        values.put(WeiboContract.UserEntry.COLUMN_FRIENDS_COUNT,friendsCount);
        values.put(WeiboContract.UserEntry.COLUMN_WEIBO_COUNT,weiboCount);

        return values;
    }
}
