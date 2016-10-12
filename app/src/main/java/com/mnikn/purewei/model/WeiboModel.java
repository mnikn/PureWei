package com.mnikn.purewei.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mnikn.mylibrary.mvp.model.BaseModel;
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


    public static final Parcelable.Creator<WeiboModel> CREATOR = new Creator<WeiboModel>() {
        @Override
        public WeiboModel createFromParcel(Parcel source) {
            WeiboModel model = new WeiboModel();
            return BaseModel.quickCreateFromParcel(model,source);
        }

        @Override
        public WeiboModel[] newArray(int size) {
            return new WeiboModel[size];
        }
    };
}
