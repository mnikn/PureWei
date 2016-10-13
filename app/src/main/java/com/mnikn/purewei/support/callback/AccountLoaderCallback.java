package com.mnikn.purewei.support.callback;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mnikn.mylibrary.adapter.EasyRecyclerCursorAdapter;
import com.mnikn.mylibrary.callback.BaseCursorLoaderCallback;
import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountLoaderCallback extends BaseCursorLoaderCallback {

    public AccountLoaderCallback(Context context, EasyRecyclerCursorAdapter adapter) {
        super(context, adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.AccountEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }
}
