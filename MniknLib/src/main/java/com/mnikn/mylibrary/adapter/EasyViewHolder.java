package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class EasyViewHolder<Model> extends RecyclerView.ViewHolder {

    private View mView;

    public EasyViewHolder(View itemView){
        super(itemView);
        mView = itemView;
    }
    public void bindView() {}
    public void bindView(Model data) {}
    public void bindView(int position,Model data) {}

    public void setOnClickListener(View.OnClickListener listener){
        mView.setOnClickListener(listener);
    }
}
