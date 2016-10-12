package com.mnikn.mylibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class LoadMoreRecyclerAdpater<T> extends EasyRecyclerAdapter<T> {

    private static final int FOOTER = -100;

    private Context mContext;

    public LoadMoreRecyclerAdpater(Context context) {
        setHasFooter(true);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() - 1){
            return FOOTER;
        }
        return getViewHolderType(position);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            return new ProcessViewHolder(new ProgressBar(mContext));
        }
        return getViewHolder(parent, viewType);
    }

    public abstract EasyViewHolder getViewHolder(ViewGroup parent, int viewType);
    public abstract int getViewHolderType(int position);

    public static class ProcessViewHolder extends EasyViewHolder{
        public ProgressBar mProcessBar;

        public ProcessViewHolder(View itemView) {
            super(itemView);
            mProcessBar = (ProgressBar) itemView;
        }
    }
}
