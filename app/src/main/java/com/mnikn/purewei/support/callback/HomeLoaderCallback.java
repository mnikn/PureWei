package com.mnikn.purewei.support.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.callback.BaseCursorLoaderCallback;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomeLoaderCallback extends BaseCursorLoaderCallback {

    public HomeLoaderCallback(Context context, RecyclerCursorAdapter adapter) {
        super(context, adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                null,
                null,
                null,
                WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
    }
}
