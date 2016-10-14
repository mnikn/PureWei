package com.mnikn.purewei.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserModel implements Parcelable {
    public int userType;
    public long uid;
    public boolean following;
    public boolean followMe;
    public String userName;
    public String description;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;
    public String coverUrl;
    public String followersCount;
    public String friendsCount;
    public String weiboCount;


    public UserModel(){}

    public UserModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        userType = WeiboContract.UserEntry.getUserType(cursor);
        uid = WeiboContract.UserEntry.getUserId(cursor);
        userName = WeiboContract.UserEntry.getName(cursor);
        description = WeiboContract.UserEntry.getDescription(cursor);
        profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
        avatarLargeUrl = WeiboContract.UserEntry.getAvatarLargeUrl(cursor);
        avatarHdUrl = WeiboContract.UserEntry.getAvatarHdUrl(cursor);
        coverUrl = WeiboContract.UserEntry.getCoverUrl(cursor);
        followersCount = NumberUtil.longToString(WeiboContract.UserEntry.getFollowerCount(cursor));
        friendsCount = NumberUtil.longToString(WeiboContract.UserEntry.getFriendsCount(cursor));
        weiboCount = NumberUtil.longToString(WeiboContract.UserEntry.getWeiboCount(cursor));
        following = WeiboContract.UserEntry.getFollowing(cursor);
        followMe = WeiboContract.UserEntry.getFollowMe(cursor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.uid);
        dest.writeByte(this.following ? (byte) 1 : (byte) 0);
        dest.writeByte(this.followMe ? (byte) 1 : (byte) 0);
        dest.writeString(this.userName);
        dest.writeString(this.description);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.avatarLargeUrl);
        dest.writeString(this.avatarHdUrl);
        dest.writeString(this.coverUrl);
        dest.writeString(this.followersCount);
        dest.writeString(this.friendsCount);
        dest.writeString(this.weiboCount);
    }

    protected UserModel(Parcel in) {
        this.uid = in.readLong();
        this.following = in.readByte() != 0;
        this.followMe = in.readByte() != 0;
        this.userName = in.readString();
        this.description = in.readString();
        this.profileImageUrl = in.readString();
        this.avatarLargeUrl = in.readString();
        this.avatarHdUrl = in.readString();
        this.coverUrl = in.readString();
        this.followersCount = in.readString();
        this.friendsCount = in.readString();
        this.weiboCount = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
