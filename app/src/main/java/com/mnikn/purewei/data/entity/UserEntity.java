package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;
import com.mnikn.purewei.support.bean.UserBean;
/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class UserEntity {
    public long userId;
    public String userName;
    public String profileImageUrl;
    public String description;
    public long followersCount;


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
    }

    private void fromUserBean(UserBean bean){
        userId = bean.id;
        userName = bean.screenName;
        profileImageUrl = bean.profileImageUrl;
        description = bean.description;
        followersCount = bean.followersCount;
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
}
