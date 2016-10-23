package com.mnikn.purewei.support.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.utils.BaseCursorLoaderCallback;
import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserLoaderCallback extends BaseCursorLoaderCallback{

    private long mUid;

    public UserLoaderCallback(Context context, EasyRecyclerAdapter adapter,long uid) {
        super(context, adapter);
        mUid = uid;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                null,
                WeiboContract.UserEntry.TABLE_NAME + "." + WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{Numbers.longToString(mUid)},
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }
}
