package com.mnikn.mylibrary.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class RecyclerCursorAdapter<VH extends EasyViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor mCursor;

    public Cursor getCursor(){
        return mCursor;
    }

    public void swapCursor(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(VH holder, int position) {
        mCursor.moveToPosition(position);
        holder.bindView(mCursor);
    }
}
