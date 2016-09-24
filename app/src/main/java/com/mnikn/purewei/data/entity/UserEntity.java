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
    public String description;
    public long followersCount;
    public long friendsCount;
    public long weiboCount;


    public UserEntity() {}
    public UserEntity(TimelineBean bean,int position){
        fromTimelineBean(bean, position);
    }
    public UserEntity(UserBean userBean){
        fromUserBean(userBean);
    }

    private void fromTimelineBean(TimelineBean bean,int position){
        TimelineBean.StatusesBean statusesBean = bean.statuses.get(position);
        userId = statusesBean.user.id;
        userName = statusesBean.user.screenName;
        profileImageUrl = statusesBean.user.profileImageUrl;
        description = statusesBean.user.description;
        followersCount = statusesBean.user.followersCount;
        friendsCount = statusesBean.user.friendsCount;
        weiboCount = statusesBean.user.statusesCount;
    }

    private void fromUserBean(UserBean bean){
        userId = bean.id;
        userName = bean.screenName;
        profileImageUrl = bean.profileImageUrl;
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
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION,description);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT,followersCount);
        values.put(WeiboContract.UserEntry.COLUMN_FRIENDS_COUNT,friendsCount);
        values.put(WeiboContract.UserEntry.COLUMN_WEIBO_COUNT,weiboCount);

        return values;
    }
}
