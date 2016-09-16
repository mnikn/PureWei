package com.mnikn.purewei.support.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;
import com.mnikn.purewei.support.bean.UserBean;
/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class UserEntity {
    private long userId;
    private String userName;
    private String profileImageUrl;
    private String description;
    private long followersCount;


    public UserEntity() {}
    public UserEntity(TimelineBean bean,int position){
        fromTimelineBean(bean, position);
    }
    public UserEntity(UserBean userBean){
        fromUserBean(userBean);
    }

    private void fromTimelineBean(TimelineBean bean,int position){
        TimelineBean.StatusesBean statusesBean = bean.getStatuses().get(position);
        userId = statusesBean.getUser().getId();
        userName = statusesBean.getUser().getScreenName();
        profileImageUrl = statusesBean.getUser().getProfileImageUrl();
        description = statusesBean.getUser().getDescription();
        followersCount = statusesBean.getUser().getFollowersCount();
    }

    private void fromUserBean(UserBean bean){
        userId = bean.getId();
        userName = bean.getScreenName();
        profileImageUrl = bean.getProfileImageUrl();
        description = bean.getDescription();
        followersCount = bean.getFollowersCount();
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.UserEntry.COLUMN_USER_ID,userId);
        values.put(WeiboContract.UserEntry.COLUMN_NAME,userName);
        values.put(WeiboContract.UserEntry.COLUMN_PROFILE_IMAGE_URL,profileImageUrl);
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION,description);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT,followersCount);

        return values;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }
}
