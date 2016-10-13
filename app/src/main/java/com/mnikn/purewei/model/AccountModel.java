package com.mnikn.purewei.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountModel implements Parcelable {

    public String token;
    public long uid;

    public AccountModel() {}
    public AccountModel(Cursor cursor) {
        token = WeiboContract.AccountEntry.getToken(cursor);
        uid = WeiboContract.AccountEntry.getUid(cursor);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeLong(this.uid);
    }

    protected AccountModel(Parcel in) {
        this.token = in.readString();
        this.uid = in.readLong();
    }

    public static final Parcelable.Creator<AccountModel> CREATOR = new Parcelable.Creator<AccountModel>() {
        @Override
        public AccountModel createFromParcel(Parcel source) {
            return new AccountModel(source);
        }

        @Override
        public AccountModel[] newArray(int size) {
            return new AccountModel[size];
        }
    };
}
