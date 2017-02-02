package com.mnikn.purewei.support.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.utils.BaseCursorLoaderCallback;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomeLoaderCallback extends BaseCursorLoaderCallback {

    public HomeLoaderCallback(Context context, EasyRecyclerAdapter adapter) {
        super(context, adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.StatusEntry.buildStatusUriWithUser(),
                null,
                null,
                null,
                WeiboContract.StatusEntry.COLUMN_CREATED_TIME + " DESC");
    }
}
