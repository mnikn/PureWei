package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyViewHolder<T> extends RecyclerView.ViewHolder {
    public EasyViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(T data);
}
