package com.mnikn.purewei.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Status implements Parcelable {
    @SerializedName("id")
    public long id;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("text")
    public String text;
    @SerializedName("source")
    public String source;
    @SerializedName("favorited")
    public boolean favorited;
    @SerializedName("user")
    public User user;
    @SerializedName("retweeted_status")
    public Status retweetStatus;
    @SerializedName("reposts_count")
    public long repostsCount;
    @SerializedName("comments_count")
    public long commentsCount;
    @SerializedName("attitudes_count")
    public long attitudesCount;
    @SerializedName("isLongText")
    public boolean isLongText;
    @SerializedName("liked")
    public boolean liked;
    @SerializedName("pic_urls")
    public List<Picture> pictures;

    protected Status(Parcel in) {
        createdAt = in.readString();
        text = in.readString();
        source = in.readString();
        favorited = in.readByte() != 0;
        retweetStatus = in.readParcelable(Status.class.getClassLoader());
        repostsCount = in.readLong();
        commentsCount = in.readLong();
        attitudesCount = in.readLong();
        isLongText = in.readByte() != 0;
        liked = in.readByte() != 0;
    }

    public Status() {

    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdAt);
        parcel.writeString(text);
        parcel.writeString(source);
        parcel.writeByte((byte) (favorited ? 1 : 0));
        parcel.writeParcelable(retweetStatus, i);
        parcel.writeLong(repostsCount);
        parcel.writeLong(commentsCount);
        parcel.writeLong(attitudesCount);
        parcel.writeByte((byte) (isLongText ? 1 : 0));
        parcel.writeByte((byte) (liked ? 1 : 0));
    }
}
