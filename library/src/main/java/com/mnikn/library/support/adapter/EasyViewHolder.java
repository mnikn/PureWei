package com.mnikn.library.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyViewHolder<Data> extends RecyclerView.ViewHolder {

    public EasyViewHolder(View itemView){
        super(itemView);
    }

    public EasyViewHolder(Context context,int layoutId,ViewGroup parent){
        this(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public abstract void bindView(int position, Data data);

    public void setOnClickListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
    }
}
