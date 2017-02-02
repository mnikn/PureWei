package com.mnikn.purewei.feature.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.EasyViewHolder;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.Status;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomeAdapter extends EasyRecyclerAdapter<CursorDataProvider,Status> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HomeAdapter(CursorDataProvider dataProvider,Context context) {
        super(dataProvider);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EasyViewHolder holder;
        View view = mLayoutInflater.inflate(R.layout.item_weibo,parent,false);
        holder = new WeiboViewHolder(mContext,view);
        return holder;
    }
}
