package com.mnikn.purewei.support.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.feature.home.HomeAdapter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CursorLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private long mUid;
    private Context mContext;
    private RecyclerCursorAdapter mAdapter;

    public CursorLoaderCallback(Context context,RecyclerCursorAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    public CursorLoaderCallback(Context context,RecyclerCursorAdapter adapter,long uid) {
        mContext = context;
        mAdapter = adapter;
        mUid = uid;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(mAdapter instanceof HomeAdapter){
            return new CursorLoader(
                    mContext,
                    WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                    null,
                    null,
                    null,
                    WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
        }
        else{
            return new CursorLoader(
                    mContext,
                    WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                    null,
                    WeiboContract.UserEntry.TABLE_NAME + "." + WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                    new String[]{NumberUtil.longToString(mUid)},
                    WeiboContract.WeiboEntry.COLUMN_CREATED_TIME + " DESC");
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
