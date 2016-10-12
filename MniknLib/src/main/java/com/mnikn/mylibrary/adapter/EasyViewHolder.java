package com.mnikn.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyViewHolder<Model> extends RecyclerView.ViewHolder {

    public EasyViewHolder(View itemView){
        super(itemView);
    }

    public EasyViewHolder(Context context,ViewGroup parent,int layoutId) {
        this(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public void bindView() {}
    public void bindView(Model data) {}
    public void bindView(int position,Model data) {}
}
