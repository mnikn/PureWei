package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.StatusesBean;
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
    public String coverUrl;
    public String description;
    public long followersCount;
    public long friendsCount;
    public long weiboCount;


    public UserEntity() {}
    public UserEntity(StatusesBean bean){
        fromTimelineBean(bean);
    }
    public UserEntity(UserBean userBean){
        fromUserBean(userBean);
    }

    private void fromTimelineBean(StatusesBean bean){
        fromUserBean(bean.user);
    }

    private void fromUserBean(UserBean bean){
        userId = bean.id;
        userName = bean.screenName;
        profileImageUrl = bean.profileImageUrl;
        avatarLargeUrl = bean.avatarLarge;
        avatarHdUrl = bean.avatarHd;
        coverUrl = bean.coverImagePhone;
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
        values.put(WeiboContract.UserEntry.COLUMN_COVER_URL,coverUrl);
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION,description);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT,followersCount);
        values.put(WeiboContract.UserEntry.COLUMN_FRIENDS_COUNT,friendsCount);
        values.put(WeiboContract.UserEntry.COLUMN_WEIBO_COUNT,weiboCount);

        return values;
    }
}
