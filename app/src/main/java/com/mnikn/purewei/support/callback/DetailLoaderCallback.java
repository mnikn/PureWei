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
public class DetailLoaderCallback extends BaseCursorLoaderCallback {

    private long mWeiboId;

    public DetailLoaderCallback(Context context, EasyRecyclerAdapter adapter,long weiboId) {
        super(context, adapter);
        mWeiboId = weiboId;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getContext(),
                WeiboContract.WeiboCommentEntry.buildWeiboCommentWithUserUri(),
                null,
                WeiboContract.WeiboCommentEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{Numbers.longToString(mWeiboId)},
                WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_TIME + " DESC");
    }
}
