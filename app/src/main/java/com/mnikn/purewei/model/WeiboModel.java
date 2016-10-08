package com.mnikn.purewei.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mnikn.mylibrary.mvp.BaseModel;
import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.util.TextUtil;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboModel extends BaseModel{
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
    public String retweetUserName;
    public String retweetText;
    public String retweetTime;
    public String retweetProfileImageUrl;
    public String retweetAvatarLargeUrl;
    public String retweetAvatarHdUrl;
    public boolean liked;

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
                retweetUserName = WeiboContract.UserEntry.getName(retweetCursor);
                retweetText = WeiboContract.WeiboEntry.getText(retweetCursor);
                retweetTime = DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(retweetCursor));
                retweetProfileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(retweetCursor);
                retweetAvatarLargeUrl = WeiboContract.UserEntry.getAvatarLargeUrl(retweetCursor);
                retweetAvatarHdUrl = WeiboContract.UserEntry.getAvatarHdUrl(retweetCursor);
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

    public static final Parcelable.Creator<WeiboModel> CREATOR = new Creator<WeiboModel>() {
        @Override
        public WeiboModel createFromParcel(Parcel source) {
            WeiboModel model = new WeiboModel();
            model.weiboId = source.readLong();
            model.userId = source.readLong();
            model.retweetId = source.readLong();
            model.reportsCount = source.readString();
            model.commentsCount = source.readString();
            model.attitudesCount = source.readString();
            model.createdTime = source.readString();
            model.text = source.readString();
            model.source = source.readString();
            model.userName = source.readString();
            model.profileImageUrl = source.readString();
            model.avatarLargeUrl = source.readString();
            model.avatarHdUrl = source.readString();
            model.retweetUserName = source.readString();
            model.retweetText = source.readString();
            model.retweetTime = source.readString();
            model.retweetProfileImageUrl = source.readString();
            model.retweetAvatarLargeUrl = source.readString();
            model.retweetAvatarHdUrl = source.readString();
            model.liked = source.readByte() != 0;
            return model;
        }

        @Override
        public WeiboModel[] newArray(int size) {
            return new WeiboModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(weiboId);
        dest.writeLong(userId);
        dest.writeLong(retweetId);
        dest.writeString(reportsCount);
        dest.writeString(commentsCount);
        dest.writeString(attitudesCount);
        dest.writeString(createdTime);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeString(userName);
        dest.writeString(profileImageUrl);
        dest.writeString(avatarLargeUrl);
        dest.writeString(avatarHdUrl);
        dest.writeString(retweetUserName);
        dest.writeString(retweetText);
        dest.writeString(retweetTime);
        dest.writeString(retweetProfileImageUrl);
        dest.writeString(retweetAvatarLargeUrl);
        dest.writeString(retweetAvatarHdUrl);
        dest.writeByte((byte) (liked ? 1 : 0));
    }
}
