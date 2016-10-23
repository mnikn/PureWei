package com.mnikn.library.utils;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseCursorLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private EasyRecyclerAdapter mAdapter;

    public BaseCursorLoaderCallback(Context context, EasyRecyclerAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swap(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swap(null);
    }

    public EasyRecyclerAdapter getAdapter(){
        return mAdapter;
    }
    public Context getContext(){
        return mContext;
    }
}
