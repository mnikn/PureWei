package com.mnikn.mylibrary.adapter.data;

import android.database.Cursor;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CursorDataProvider implements DataProvider<Cursor,Cursor> {

    private Cursor mCursor;

    @Override
    public Cursor getData(int position) {
        mCursor.moveToPosition(position);
        return mCursor;
    }

    @Override
    public Cursor getDataContainer() {
        return mCursor;
    }

    @Override
    public boolean isEmpty() {
        return mCursor.getCount() == 0;
    }

    @Override
    public void swap(Cursor dataContainer) {
        mCursor = dataContainer;
    }
}
