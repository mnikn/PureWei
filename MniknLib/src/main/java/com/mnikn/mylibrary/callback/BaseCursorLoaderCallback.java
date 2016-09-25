package com.mnikn.mylibrary.callback;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseCursorLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private RecyclerCursorAdapter mAdapter;

    public BaseCursorLoaderCallback(Context context,RecyclerCursorAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public RecyclerCursorAdapter getAdapter(){
        return mAdapter;
    }
    public Context getContext(){
        return mContext;
    }
}
