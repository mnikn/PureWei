package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.purewei.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomeAdapter extends RecyclerCursorAdapter<EasyViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HomeAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EasyViewHolder holder;
        View view = mLayoutInflater.inflate(R.layout.item_home,parent,false);
        holder = new WeiboViewHolder(mContext,view);
        return holder;
    }
}
