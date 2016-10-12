package com.mnikn.mylibrary.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyRecyclerCursorAdapter extends RecyclerView.Adapter<EasyViewHolder> {

    private Cursor mCursor;
    private boolean mHasHeader;
    private boolean mHasFooter;

    public Cursor getCursor(){
        return mCursor;
    }

    public void swapCursor(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
    }

    public void setHasHeader(boolean hasHeader){
        mHasHeader = hasHeader;
    }
    public void setHasFooter(boolean hasFooter){
        mHasFooter = hasFooter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        if(mHasHeader && !mHasFooter){
            if(position == 0){
                holder.bindView();
            }
            else{
                mCursor.moveToPosition(position - 1);
                holder.bindView(mCursor);
            }
        }
        else if(mHasFooter && !mHasHeader){
            if(position == mCursor.getCount()){
                holder.bindView();
            }
            else{
                mCursor.moveToPosition(position);
                holder.bindView(mCursor);
            }
        }
        else if(mHasHeader && mHasFooter){
            if(position == 0 || position == mCursor.getCount() + 1){
                holder.bindView();
            }
            else{
                mCursor.moveToPosition(position - 1);
                holder.bindView(mCursor);
            }
        }
        else{
            mCursor.moveToPosition(position);
            holder.bindView(mCursor);
        }
    }

    @Override
    public int getItemCount() {
        int size;
        int cursorSize = ( mCursor == null ? 0 : mCursor.getCount());
        if(mHasHeader && mHasFooter){
            size = cursorSize + 2;
        }
        else if(mHasHeader || mHasFooter){
            size = cursorSize + 1;
        }
        else{
            size = cursorSize;
        }
        return size;
    }
}
