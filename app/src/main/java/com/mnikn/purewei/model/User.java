package com.mnikn.purewei.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class User implements Parcelable {

    public int type;

    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("profile_image_url")
    public String profileImageUrl;
    @SerializedName("cover_image")
    public String coverImage;
    @SerializedName("followers_count")
    public long followersCount;
    @SerializedName("friends_count")
    public long friendsCount;
    @SerializedName("statuses_count")
    public long statusesCount;
    @SerializedName("following")
    public boolean following;
    @SerializedName("avatar_large")
    public String avatarLarge;
    @SerializedName("avatar_hd")
    public String avatarHd;
    @SerializedName("follow_me")
    public boolean followMe;

    protected User(Parcel in) {
        type = in.readInt();
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        profileImageUrl = in.readString();
        coverImage = in.readString();
        followersCount = in.readLong();
        friendsCount = in.readLong();
        statusesCount = in.readLong();
        following = in.readByte() != 0;
        avatarLarge = in.readString();
        avatarHd = in.readString();
        followMe = in.readByte() != 0;
    }

    public User() {

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(profileImageUrl);
        parcel.writeString(coverImage);
        parcel.writeLong(followersCount);
        parcel.writeLong(friendsCount);
        parcel.writeLong(statusesCount);
        parcel.writeByte((byte) (following ? 1 : 0));
        parcel.writeString(avatarLarge);
        parcel.writeString(avatarHd);
        parcel.writeByte((byte) (followMe ? 1 : 0));
    }
}
