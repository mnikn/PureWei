package com.mnikn.purewei.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboModel implements Parcelable {
    public long weiboId;
    public long userId;
    public long retweetId;
    public String reportsCount;
    public String commentsCount;
    public String attitudesCount;
    public String createdTime;
    public String text;
    public String source;
    public String userName;
    public String profileImageUrl;
    public String avatarLargeUrl;
    public String avatarHdUrl;
    public boolean liked;
    public WeiboModel retweetModel;

    public WeiboModel(){}

    public WeiboModel(Cursor cursor){
        fromCursor(cursor);
    }

    public void fromCursor(Cursor cursor){
        if(cursor == null) return;

        retweetId = WeiboContract.WeiboEntry.getRetweetId(cursor);
        if(!NumberUtil.isZero(retweetId)){
            Context context = App.getAppContext();
            Cursor retweetCursor = context.getContentResolver().query(
                    WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                    null,
                    "(" + WeiboContract.WeiboEntry.COLUMN_WEIBO_ID+ " = ?"
                            + ") GROUP BY (" + WeiboContract.WeiboEntry.COLUMN_WEIBO_ID + ")",
                    new String[]{NumberUtil.longToString(retweetId)},
                    null);
            if(retweetCursor != null){
                retweetCursor.moveToFirst();
                retweetModel = new WeiboModel(retweetCursor);
                retweetCursor.close();
            }

        }

        weiboId = WeiboContract.WeiboEntry.getWeiboId(cursor);
        userId = WeiboContract.WeiboEntry.getUserId(cursor);
        reportsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getReportsCount(cursor));
        commentsCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getCommentsCount(cursor));
        attitudesCount = NumberUtil.longToString(WeiboContract.WeiboEntry.getAttitudesCount(cursor));
        createdTime = DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor));
        text = WeiboContract.WeiboEntry.getText(cursor);
        source = TextUtil.cutHerfInfo(WeiboContract.WeiboEntry.getSource(cursor));
        liked = WeiboContract.WeiboEntry.getLiked(cursor);

        userName = WeiboContract.UserEntry.getName(cursor);
        profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
        avatarLargeUrl = WeiboContract.UserEntry.getAvatarLargeUrl(cursor);
        avatarHdUrl = WeiboContract.UserEntry.getAvatarHdUrl(cursor);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.weiboId);
        dest.writeLong(this.userId);
        dest.writeLong(this.retweetId);
        dest.writeString(this.reportsCount);
        dest.writeString(this.commentsCount);
        dest.writeString(this.attitudesCount);
        dest.writeString(this.createdTime);
        dest.writeString(this.text);
        dest.writeString(this.source);
        dest.writeString(this.userName);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.avatarLargeUrl);
        dest.writeString(this.avatarHdUrl);
        dest.writeByte(this.liked ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.retweetModel, flags);
    }

    protected WeiboModel(Parcel in) {
        this.weiboId = in.readLong();
        this.userId = in.readLong();
        this.retweetId = in.readLong();
        this.reportsCount = in.readString();
        this.commentsCount = in.readString();
        this.attitudesCount = in.readString();
        this.createdTime = in.readString();
        this.text = in.readString();
        this.source = in.readString();
        this.userName = in.readString();
        this.profileImageUrl = in.readString();
        this.avatarLargeUrl = in.readString();
        this.avatarHdUrl = in.readString();
        this.liked = in.readByte() != 0;
        this.retweetModel = in.readParcelable(WeiboModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<WeiboModel> CREATOR = new Parcelable.Creator<WeiboModel>() {
        @Override
        public WeiboModel createFromParcel(Parcel source) {
            return new WeiboModel(source);
        }

        @Override
        public WeiboModel[] newArray(int size) {
            return new WeiboModel[size];
        }
    };
}
