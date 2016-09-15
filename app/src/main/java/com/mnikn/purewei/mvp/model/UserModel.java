package com.mnikn.purewei.mvp.model;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.support.bean.WeiboBean;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class UserModel {

    private String userName;
    private String profileImageUrl;
    private String description;
    private long userId;
    private long followersCount;

    public UserModel(){}

    public UserModel(WeiboBean bean,int position){
        fromBean(bean,position);
    }

    public UserModel(UserBean bean){
        fromBean(bean);
    }

    public void fromBean(WeiboBean bean,int position){
        userName = bean.getStatuses().get(position).getUser().getScreenName();
        profileImageUrl = bean.getStatuses().get(position).getUser().getProfileImageUrl();
        description = bean.getStatuses().get(position).getUser().getDescription();
        userId = bean.getStatuses().get(position).getUser().getId();
        followersCount = bean.getStatuses().get(position).getUser().getFollowersCount();
    }

    public void fromBean(UserBean bean){
        userName = bean.getScreenName();
        profileImageUrl = bean.getProfileImageUrl();
        description = bean.getDescription();
        userId = bean.getId();
        followersCount = bean.getFollowersCount();
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.UserEntry.COLUMN_NAME,userName);
        values.put(WeiboContract.UserEntry.COLUMN_PROFILE_IMAGE_URL,profileImageUrl);
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION,description);
        values.put(WeiboContract.UserEntry.ID,userId);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT,followersCount);
        return values;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }
}
