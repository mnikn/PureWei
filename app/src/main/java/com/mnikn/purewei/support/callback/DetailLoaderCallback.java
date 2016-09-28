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
public class DetailLoaderCallback extends BaseCursorLoaderCallback {

    public DetailLoaderCallback(Context context, RecyclerCursorAdapter adapter) {
        super(context, adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.WeiboCommentEntry.buildWeiboCommentWithUserUri(),
                null,
                null,
                null,
                WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_TIME + " DESC");
    }
}
