package com.mnikn.purewei.model;

import android.database.Cursor;
import android.os.Parcel;

import com.mnikn.mylibrary.mvp.BaseModel;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserModel extends BaseModel{
    public String userName;
    public String description;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;
    public String coverUrl;
    public String followersCount;
    public String friendsCount;
    public String weiboCount;
    public boolean fllowing;
    public boolean fllowMe;

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
        coverUrl = WeiboContract.UserEntry.getCoverUrl(cursor);
        followersCount = NumberUtil.longToString(WeiboContract.UserEntry.getFollowerCount(cursor));
        friendsCount = NumberUtil.longToString(WeiboContract.UserEntry.getFriendsCount(cursor));
        weiboCount = NumberUtil.longToString(WeiboContract.UserEntry.getWeiboCount(cursor));
        fllowing = WeiboContract.UserEntry.getFllowing(cursor);
        fllowMe = WeiboContract.UserEntry.getFllowMe(cursor);
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            UserModel model = new UserModel();
            return BaseModel.quickCreateFromParcel(model,source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
